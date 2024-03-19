package com.gstdev.template.gateway.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
public class SignUtil {

  /**
   * sign是否超时
   *
   * @param time
   * @return
   */
  public static boolean checkTime(long time) {
    long now = System.currentTimeMillis();
    //超过5分钟
    if (now - time > 30 * 60 * 1000) {
      return false;
    } else {
      return true;
    }
  }

  public static String utf8Encoding(String value, String sourceCharsetName) {
    try {
      return new String(value.getBytes(sourceCharsetName), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new IllegalArgumentException(e);
    }
  }


  private static byte[] getMD5Digest(String data) throws IOException {
    byte[] bytes = null;
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      bytes = md.digest(data.getBytes("UTF-8"));
    } catch (GeneralSecurityException gse) {
      throw new IOException(gse);
    }
    return bytes;
  }


  private static String byte2hex(byte[] bytes) {
    StringBuilder sign = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      String hex = Integer.toHexString(bytes[i] & 0xFF);
      if (hex.length() == 1) {
        sign.append("0");
      }
      sign.append(hex.toUpperCase());
      //sign.append(hex.toLowerCase());
    }
    return sign.toString();
  }


  /**
   * 得到签名
   *
   * @param params 参数集合不含secretkey
   * @param secret 验证接口的secretkey
   * @return
   */
  public static String getSign(Map<String, String> params, String timestamp, String secret) {
    String sign = "";
    StringBuilder sb = new StringBuilder();
    //step1：先对请求参数排序
    Set<String> keyset = params.keySet();
    TreeSet<String> sortSet = new TreeSet<String>();
    sortSet.addAll(keyset);
    Iterator<String> it = sortSet.iterator();
    //step2：把参数的key value链接起来 secretkey放在最后面，得到要加密的字符串
    while (it.hasNext()) {
      String key = it.next();
      String value = params.get(key);
      sb.append(key).append("=").append(value).append("&");
    }
    sb.append("timestamp=").append(timestamp).append("&");
    sb.append("secret=").append(secret);

    log.debug(sb.toString());
    byte[] md5Digest;
    try {
      //得到Md5加密得到sign
      md5Digest = getMD5Digest(sb.toString());
      sign = byte2hex(md5Digest);
    } catch (IOException e) {
      log.error("生成签名错误", e);
    }
    log.debug("sign:" + sign);
    return sign;
  }


}
