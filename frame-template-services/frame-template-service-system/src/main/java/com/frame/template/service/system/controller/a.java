package com.frame.template.service.system.controller;

import com.gstdev.cloud.base.core.utils.SecureUtil;
import com.gstdev.cloud.message.core.definition.event.AbstractApplicationEvent;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.stripe.exception.StripeException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.IOException;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
public class a {



        // 生成 AES 密钥
        public static SecretKey generateKey() throws Exception {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256); // 256 位密钥
            return keyGenerator.generateKey();
        }

        // AES 加密
        public static byte[] encrypt(String data, SecretKey secretKey) throws Exception {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data.getBytes("UTF-8"));
        }

        // GZIP 压缩
        public static byte[] compress(byte[] data) throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(data);
            gzipOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }

        // Base64 编码
        public static String encodeToBase64(byte[] data) {
            return Base64.getEncoder().encodeToString(data);
        }

        // Base64 解码
        public static byte[] decodeFromBase64(String data) {
            return Base64.getDecoder().decode(data);
        }

        // GZIP 解压
        public static byte[] decompress(byte[] compressedData) throws IOException {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedData);
            GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = gzipInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream.toByteArray();
        }

        // AES 解密
        public static String decrypt(byte[] encryptedData, SecretKey secretKey) throws Exception {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedData);
            return new String(decryptedBytes, "UTF-8");
        }

        // 解密并解压缩
        public static String decryptAndDecompress(String encryptedBase64, SecretKey secretKey) throws Exception {
            // Step 1: Base64 解码
            byte[] compressedData = decodeFromBase64(encryptedBase64);

            // Step 2: GZIP 解压
            byte[] decryptedData = decompress(compressedData);

            // Step 3: AES 解密
            return decrypt(decryptedData, secretKey);
        }

        // 示例：加密、压缩和解密过程
        public static void main(String[] args) throws Exception {
            // 生成 AES 密钥
            SecretKey secretKey = generateKey();

            // 测试超长字符串
            StringBuilder longStringBuilder = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longStringBuilder.append("HelloWorldHelloWorldHelloWorldHelloWorldHelloWorld");
            }
            String longString = longStringBuilder.toString();

            // Step 1: AES 加密
            byte[] encryptedBytes = encrypt(longString, secretKey);

            // Step 2: 压缩加密结果
            byte[] compressedBytes = compress(encryptedBytes);

            // Step 3: Base64 编码压缩后的加密数据
            String encodedString = encodeToBase64(compressedBytes);

            // 输出加密后且压缩后的 Base64 编码结果
            System.out.println("加密后且压缩后的 Base64 编码结果：");
            System.out.println(encodedString);

            // Step 4: 解密并解压缩
            String decryptedData = decryptAndDecompress(encodedString, secretKey);

            // 输出解密后的数据
            System.out.println("解密后的数据：");
            System.out.println(decryptedData);

            // 验证解密后的数据是否与原始数据一致
            System.out.println("解密数据是否一致: " + longString.equals(decryptedData));
        }


}
