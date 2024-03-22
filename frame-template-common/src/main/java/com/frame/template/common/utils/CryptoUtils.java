package com.frame.template.common.utils;

import com.frame.template.common.constant.ServiceConstants;
import com.frame.template.common.utils.crypto.AsymKeyPairs;
import com.frame.template.common.utils.crypto.SymKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 敏感数据加解密、租户密钥加解密、生成密钥的的工具类
 *
 * @Author: zcy
 * @Data: 2022/12/07
 * @Description:
 */

public final class CryptoUtils {

  private static final Logger LOG = LoggerFactory.getLogger(CryptoUtils.class);

  private static final String AES = "AES";
  private static final String RSA = "RSA";

  private static final String RSA_TRANSFORMATION = "RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING";

  /**
   * 租户用于敏感数据加密的对称算法的transformation, 要和mysql中aes_encrypt使用的统一
   * java加解密说明
   * https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html
   * mysql中的说明
   * https://dev.mysql.com/doc/refman/8.0/en/encryption-functions.html#function_aes-encrypt
   */
  private static final String TENANT_AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";
  /**
   * 租户的对称密钥，用于加密敏感数据，长度256bit
   */
  private static final int TENANT_SYM_KEY_SIZE = 256;
  /**
   * master key，用于加密租户的对称密钥，长度256bit
   */
  private static final int MASTER_KEY_SIZE = 256;
  /**
   * 租户的非对称密钥，长度2048
   */
  private static final int TENANT_ASYM_KEY_SIZE = 2048;

  private CryptoUtils() {
    throw new UnsupportedOperationException("工具类无法被实例化.");
  }

  /**
   * 加密敏感信息，这个方法需要和mysql中的加密函数aes_encrypt的结果保持一致 <br />
   * mysql加密逻辑如下：<br />
   * varchar -> utf8mb4反编码为byte[] -> 通过AES_ENCRYPT函数转为加密后的byte[] -> 通过HEX函数转成字符串 <br />
   * Java程序使用相同的逻辑完成这个工作 <br />
   * <p>
   * sensitivePlainText为null时，返回null， <br />
   * sensitivePlainText为空字符串时，不会返回空字符串，返回加密的后的hex，<br />
   * 出现异常时，日志中不会记录原始的敏感数据和密钥信息，需要上层调用方捕获异常，记录业务信息 <br />
   *
   * @param sensitivePlainText 需要加密的敏感数据,明文，例如“张三”,"13012345678"
   * @param hexKey             租户hex后的密钥
   * @return hex后的密文文本
   */
  public static String encryptSensitive(String sensitivePlainText, String hexKey) {
    if (sensitivePlainText == null) {
      return null;
    }
    SymKey symkey = new SymKey();
    symkey.setHexKey(hexKey);
    //mysql utf8mb4 对应于java的 StandardCharsets.UTF_8
    byte[] plainBytes = sensitivePlainText.getBytes(StandardCharsets.UTF_8);
    return HexUtils.bytesToHexString(aesEncrypt(plainBytes, symkey, TENANT_AES_TRANSFORMATION));
  }

  /**
   * 解密敏感信息，这个方法需要和mysql中的加密函数aes_decrypt的结果保持一致 <br />
   * mysql解密逻辑如下：<br />
   * <p>
   * Hex的字符串->通过UNHEX转为加密后的byte[] -> 通过AES_DECRYPT函数转为解密后的byte[]-> 通过utf8mb4编码转为原文 <br />
   * Java程序使用相同的逻辑完成这个工作 <br />
   * <p>
   * sensitiveCipherText为null时，返回null，为空字符串时，返回null ,这是由于mysql在空字符串''上使用aes_decrypt函数返回null<br />
   * 出现异常时，日志中不会记录原始的敏感数据和密钥信息，需要上层调用方捕获异常，记录业务信息 <br />
   *
   * @param sensitiveCipherText 需要解密的敏感数据,hex形式的字符串
   * @param hexKey              租户的对称加密密钥
   * @return
   */
  public static String decryptSensitive(String sensitiveCipherText, String hexKey) {
    if (sensitiveCipherText == null) {
      return null;
    }
    if ("".equals(sensitiveCipherText)) {
      return null;
    }
    SymKey symKey = new SymKey();
    symKey.setHexKey(hexKey);

    byte[] cipherBytes = HexUtils.hexStringToBytes(sensitiveCipherText);
    //mysql utf8mb4 对应于java的 StandardCharsets.UTF_8
    return new String(aesDecrypt(cipherBytes, symKey, TENANT_AES_TRANSFORMATION), StandardCharsets.UTF_8);
  }

  /**
   * 使用master key加密租户的密钥
   *
   * @param hexPlainTenantKey hex形式的租户密钥
   * @param hexMasterKey      hex形式的master key
   * @return 加密后的hex形式的租户密钥
   */
  public static String encryptWithMasterKey(String hexPlainTenantKey, String hexMasterKey) {
    if (hexMasterKey == null || "".equals(hexMasterKey)) {
      throw new RuntimeException("hexMasterKey为null或者空字符串，请检查masterKey的配置");
    }
    if (hexPlainTenantKey == null || "".equals(hexPlainTenantKey)) {
      throw new RuntimeException("传入的租户hex密钥为空null或者空字符串,请检查租户密钥配置");
    }
        /*
         这里从string转为byte[]的逻辑和敏感数据加密方法encryptSensitive中的不同，
         由于hexPlainTenantKey是由byte[]转来的，所以可以再转回byte[]，这种写法保证了加密后和加密前的租户密钥长度一致，更具有混淆性
         */
    byte[] plainTenantKeyBytes = HexUtils.hexStringToBytes(hexPlainTenantKey);
    SymKey symKey = new SymKey();
    symKey.setHexKey(hexMasterKey);
    return HexUtils.bytesToHexString(aesEncrypt(plainTenantKeyBytes, symKey, TENANT_AES_TRANSFORMATION));
  }

  /**
   * 使用master key解密租户密钥
   *
   * @param hexCipherTenantKey hex形式的加密后的租户密钥
   * @param hexMasterKey       hex形式的master key
   * @return hex形式的租户密钥
   */
  public static String decryptWithMasterKey(String hexCipherTenantKey, String hexMasterKey) {
    if (hexMasterKey == null || "".equals(hexMasterKey)) {
      throw new RuntimeException("hexMasterKey为null或者空字符串，请检查masterKey的配置");
    }
    if (hexCipherTenantKey == null || "".equals(hexCipherTenantKey)) {
      throw new RuntimeException("传入的租户加密后hex密钥为空null或者空字符串,请检查租户密钥配置");
    }
    byte[] cipherTenantKeyBytes = HexUtils.hexStringToBytes(hexCipherTenantKey);
    SymKey symKey = new SymKey();
    symKey.setHexKey(hexMasterKey);
    return HexUtils.bytesToHexString(aesDecrypt(cipherTenantKeyBytes, symKey, TENANT_AES_TRANSFORMATION));
  }

  /**
   * 非对称加密，使用ras实现
   * 出现异常时，日志中不会记录原始的数据和密钥信息，需要上层调用方捕获异常，记录业务信息 <br />
   *
   * @param plainText,   需要加密的明文，例如“张三”，为null或者length=0是，返回空的byte[]<br />
   * @param hexPublicKey hex格式的公钥
   * @return 加密后的byte[], 如果需要hex使用com.xsd.hrsaas.common.utils.HexUitls，如果需要base64格式则使用 java.uitl.Base64做转换
   */
  public static byte[] asymEncrypt(String plainText, String hexPublicKey) {
    if (plainText == null || plainText.length() == 0) {
      return new byte[0];
    }
    return rsaEncrypt(plainText.getBytes(StandardCharsets.UTF_8), HexUtils.hexStringToBytes(hexPublicKey));
  }

  /**
   * 非对称解密，使用ras实现
   * 出现异常时，日志中不会记录加密后数据和密钥信息，需要上层调用方捕获异常，记录业务信息 <br />
   *
   * @param cipherBytes,  需要解密的bytes[]
   * @param hexPrivateKey hex格式的私钥
   * @return 解密后的byte[], 需要转为String使用 new String(bytes,StandardCharsets.UTF_8);
   */
  public static byte[] asymDecrypt(byte[] cipherBytes, String hexPrivateKey) {
    if (cipherBytes == null || cipherBytes.length == 0) {
      return new byte[0];
    }
    return rsaDecrypt(cipherBytes, HexUtils.hexStringToBytes(hexPrivateKey));
  }

  public static void main(String[] args) {
//    AsymKeyPairs pairs = generateAsymKeypair();
//    System.out.println(pairs.getHexPrivateKey());
//    System.out.println(pairs.getHexPublicKey());
    System.out.println(Base64.getEncoder().encodeToString(CryptoUtils.asymEncrypt("123456", ServiceConstants.ASYM_PUBLIC_KEY)));
  }

  /**
   * 生成租户的对称加密密钥
   *
   * @return
   */
  public static SymKey generateSymKey() {
    byte[] keyBytes = generateAESKey(TENANT_SYM_KEY_SIZE);
    SymKey symKey = new SymKey();
    symKey.setHexKey(HexUtils.bytesToHexString(keyBytes));
    symKey.setKeySize(TENANT_SYM_KEY_SIZE);
    return symKey;
  }

  /**
   * 生成租户的非对称加密密钥
   *
   * @return
   */
  public static AsymKeyPairs generateAsymKeypair() {
    KeyPair keyPair = generateRSAKeyPair(TENANT_ASYM_KEY_SIZE);
    AsymKeyPairs asymKeyPairs = new AsymKeyPairs();
    asymKeyPairs.setHexPublicKey(HexUtils.bytesToHexString(keyPair.getPublic().getEncoded()));
    asymKeyPairs.setHexPrivateKey(HexUtils.bytesToHexString(keyPair.getPrivate().getEncoded()));
    return asymKeyPairs;
  }

  /**
   * 生成master key的方法，master key是一个256位的对称加密密钥
   *
   * @return
   */
  public static SymKey generateMasterKey() {
    byte[] keyBytes = generateAESKey(MASTER_KEY_SIZE);
    SymKey symKey = new SymKey();
    symKey.setHexKey(HexUtils.bytesToHexString(keyBytes));
    return symKey;
  }

  private static KeyPair generateRSAKeyPair(int keySize) {
    KeyPairGenerator generator;
    try {
      generator = KeyPairGenerator.getInstance(RSA);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("获取算法生成器是出现异常," + e.getMessage(), e);
    }
    generator.initialize(keySize);
    return generator.generateKeyPair();
  }

  private static byte[] rsaEncrypt(byte[] plainBytes, byte[] publicKey) {
    if (publicKey == null || publicKey.length == 0) {
      throw new RuntimeException("传入的用于加密的公钥为空");
    }
    try {
      Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
      PublicKey key = KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(publicKey));
      cipher.init(Cipher.ENCRYPT_MODE, key);
      return cipher.doFinal(plainBytes);
    } catch (Exception e) {
      throw new RuntimeException("加密敏感数据时出现错误", e);
    }
  }

  private static byte[] rsaDecrypt(byte[] cipherBytes, byte[] privateKey) {
    if (privateKey == null || privateKey.length == 0) {
      throw new RuntimeException("传入的用于解密的私钥为空");
    }
    try {
      Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
      PrivateKey key = KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(privateKey));
      cipher.init(Cipher.DECRYPT_MODE, key);
      return cipher.doFinal(cipherBytes);
    } catch (Exception e) {
      throw new RuntimeException("解密敏感数据时出现错误", e);
    }
  }

  private static byte[] generateAESKey(int keySize) {
    if (keySize <= 0) {
      throw new RuntimeException("keySize必须是正数");
    }
    SecureRandom rand = new SecureRandom();
    KeyGenerator generator;
    try {
      generator = KeyGenerator.getInstance(AES);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("获取算法生成器时出现异常," + e.getMessage(), e);
    }
    generator.init(keySize, rand);
    return generator.generateKey().getEncoded();
  }

  private static byte[] aesEncrypt(byte[] plainBytes, SymKey key, String cipherTransformation) {
    if (key == null || key.getHexKey() == null || "".equals(key.getHexKey())) {
      throw new RuntimeException("传入的密钥为空");
    }
    try {
      Cipher cipher = Cipher.getInstance(cipherTransformation);
      SecretKey secretKey = new SecretKeySpec(HexUtils.hexStringToBytes(key.getHexKey()), AES);
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      return cipher.doFinal(plainBytes);
    } catch (Exception e) {
      LOG.error("加密敏感数据时出现错误");
      throw new RuntimeException(e);
    }
  }

  private static byte[] aesDecrypt(byte[] cipherBytes, SymKey key, String cipherTransformation) {
    if (key == null || key.getHexKey() == null || "".equals(key.getHexKey())) {
      throw new RuntimeException("传入的密钥为空");
    }
    try {
      Cipher cipher = Cipher.getInstance(cipherTransformation);
      SecretKey secretKey = new SecretKeySpec(HexUtils.hexStringToBytes(key.getHexKey()), AES);
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
      return cipher.doFinal(cipherBytes);
    } catch (Exception e) {
      LOG.error("解密敏感数据时出现错误");
      throw new RuntimeException(e);
    }
  }
}
