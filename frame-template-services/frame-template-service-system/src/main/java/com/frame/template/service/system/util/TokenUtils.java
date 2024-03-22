package com.frame.template.service.system.util;


import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Random;

@Deprecated
public class TokenUtils {

  private TokenUtils(){
  }

  private static final TokenUtils instance = new TokenUtils();

  public static TokenUtils getInstance(){
    return instance;
  }

  public String randomCode(){
//    return UUID.randomUUID().toString().replaceAll("-", "");
    String token = (System.currentTimeMillis() +"@@"+ new Random().nextInt(999999999)) + "";
    Base64 encoder = new Base64();
    String newStr = encoder.encodeBase64String(token.getBytes());
    return newStr.replaceAll("=","");
  }

  public String encode(){
    String token = (System.currentTimeMillis() +"@@"+ new Random().nextInt(999999999)) + "";
    Base64 encoder = new Base64();
    String newStr = "";
    newStr = encoder.encodeBase64String(token.getBytes());
    return newStr;
  }

//  public String decode(String token){
//    Base64 decoder = new Base64();
//    String newStr = "";
//    try {
//      newStr = new String(decoder.decodeBase64(token.getBytes()));
//    } catch (Exception ex) {
//      ex.printStackTrace();
//      return "Invalid token!";
//    }
//    return newStr;
//  }

}
