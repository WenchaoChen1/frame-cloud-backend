//package com.spting.auth.config;
//
//import com.alibaba.fastjson.JSONObject;
//import com.gstdev.cloud.commons.utils.RedisUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import jakarta.annotation.Resource;
//import java.net.URI;
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;
//
//@Component
//public class TokenUtil {
//
//  @Autowired
//  private JwtConfig jwtConfig;
//  @Resource
//  private RedisUtils redisUtils;
//
//
//  public String getToken() {
//    try {
//      String key = "temporary_token";
//      Object o = redisUtils.get(key);
//      if (o != null) {
//        return String.valueOf(o);
//      }
//      // 设置请求的 URL 和凭据信息
//      URI uri = new URI(jwtConfig.getIssuerUri() + "/oauth2/token");
//      String clientId = "credentials-client";
//      String clientSecret = "black123";
//      // 构造请求体
//      String requestBody = "grant_type=client_credentials";
//      // 添加 Basic Auth 认证头
//      String encodedCredentials = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));
//      // 发送请求
//      HttpClient client = HttpClientBuilder.create().build();
//      HttpPost post = new HttpPost(uri);
//      post.setHeader(org.apache.http.HttpHeaders.AUTHORIZATION, "Basic " + encodedCredentials);
//      post.setHeader(org.apache.http.HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
//      post.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));
//      HttpResponse response = client.execute(post);
//      HttpEntity entity = response.getEntity();
//      if (entity != null) {
//        String responseString = EntityUtils.toString(entity);
//        JSONObject jsonObject = JSONObject.parseObject(responseString);
//        String token = jsonObject.getString("access_token");
//        redisUtils.set(key, token, 2500000);
//        return token;
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return null;
//  }
//
//}
