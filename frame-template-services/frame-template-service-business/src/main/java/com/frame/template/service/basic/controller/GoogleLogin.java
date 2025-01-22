package com.frame.template.service.basic.controller;

import cn.hutool.http.Header;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.http.param.MediaType;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.GeneralSecurityException;
import java.util.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/musician")
public class GoogleLogin {

    @Value("${auth.google.client_id}")
    private String client_id;
    @Value(value = "${auth.google.client_secret}")
    private String client_secret;
    @PostMapping("/google/logintest")
    public void googleLogintest(@RequestParam("credential") String credential) throws GeneralSecurityException, IOException {
        getAssesstoken(credential);
    }

    @GetMapping("/googleLoginUrl")
    public String googleLoginUrl(HttpServletResponse response){
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        List<String> SCOPES =
            Arrays.asList("https://www.googleapis.com/auth/userinfo.email");

        // 设置 OAuth 2.0 授权码流对象
        AuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            httpTransport, jsonFactory, client_id, client_secret, SCOPES)
            .setAccessType("offline")
            .setApprovalPrompt("force") // 可选，强制用户重新授权
            .build();

        // 生成用户授权的 URL
        AuthorizationCodeRequestUrl authorizationUrl = flow.newAuthorizationUrl()
            .setRedirectUri("https://www.baidu.com");
            //.setRedirectUri("http://localhost:8103/musician/google/login");

        return authorizationUrl.build();
    }

    @GetMapping("/google/login")
    public String getCode(HttpServletRequest request) {
        System.out.println("调用code接口");
        System.out.println(request.getParameter("code"));
        return request.getParameter("code");
    }

    private GoogleTokenResponse getTokenResponse(String authCode) {
        String proxyHost = "your.proxy.host";
        int proxyPort = 8080;

        // 设置代理
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));

        // 设置系统属性，使得HTTP客户端使用代理
        System.setProperty("http.proxyHost", proxyHost);
        System.setProperty("http.proxyPort", Integer.toString(proxyPort));




        GoogleAuthorizationCodeTokenRequest  tokenRequest = new GoogleAuthorizationCodeTokenRequest(
            new NetHttpTransport(), JacksonFactory.getDefaultInstance(),
            //"https://www.googleapis.com/oauth2/v1/token",
            //"https://oauth2.googleapis.com/token",
            "https://oauth2.googleapis.com/token",
            client_id, client_secret,
            authCode, "http://localhost:8103/musician/google/login").setGrantType("authorization_code");
        try {
            return tokenRequest.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void getAssesstoken(String code) {
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("client_id", client_id);
            params.put("client_secret", client_secret);
            params.put("grant_type", "authorization_code");
            params.put("code", code);
            params.put("redirect_uri", "http://localhost:8103/musician/google/login");

            String str = JSONObject.toJSONString(params);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7980));
            String result = HttpUtil.createPost("https://oauth2.googleapis.com/token").body(str).setProxy(proxy).execute().body();
            JSONObject json = JSONObject.parseObject(result);
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList("158104564519-voc1vdk1mo9ujag8qu2iu4o6o1mmviad.apps.googleusercontent.com"))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

            // (Receive idTokenString by HTTPS POST)
            // 这里验证登录回调的credential完整性
            if(json.getString("id_token")!=null){
                System.out.println(json.getString("id_token"));
                System.out.println(json.getString("access_token"));
                getUserInfo(json.getString("id_token"));
            }
            System.out.println(result);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getUserInfo(String accessToken){
        //使用代理时使用
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7980));
        Map<String, String> headers = new HashMap<>();
        String result = HttpUtil.createGet("https://www.googleapis.com/oauth2/v2/userinfo").header("Authorization", "Bearer " + accessToken).setProxy(proxy).execute().body();
        JSONObject json = JSONObject.parseObject(result);
    }

    @GetMapping("/google/getUserInfo")
    public void getCode(@RequestParam("accessToken") String accessToken) {
        getUserInfo(accessToken);
    }
}


