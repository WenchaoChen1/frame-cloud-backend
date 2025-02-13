package com.frame.template.service.basic.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.GeneralSecurityException;
import java.util.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/musician")
public class FacebookLogin {

    @Value("${auth.facebook.client_id}")
    private String client_id;
    @Value(value = "${auth.facebook.client_secret}")
    private String client_secret;
    @PostMapping("/facebook/loginTest")
    public JSONObject FacebookLoginTest(@RequestParam("credential") String credential) throws GeneralSecurityException, IOException {
        return getAssesstoken(credential);
    }

    @GetMapping("/facebookLoginUrl")
    public String googleLoginUrl(HttpServletResponse response){
        String url = "https://www.facebook.com/v3.3/dialog/oauth" +
            "?response_type=code" +
            "&client_id=" + client_id +
            //"&redirect_uri="+ "http://localhost:8103/musician/facebook/login" +
            //"&redirect_uri="+ "http://localhost:8081/draft" +

            "&scope=email&name&";
        return url;
    }

    @GetMapping("/facebook/login")
    public String getCode(HttpServletRequest request) {
        System.out.println("调用code接口");
        System.out.println(request.getParameter("code"));
        return request.getParameter("code");
    }

    private JSONObject getAssesstoken(String code) {
        JSONObject result = new JSONObject();
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("client_id", client_id);
            params.put("client_secret", client_secret);
            params.put("code", code);
            //params.put("redirect_uri", "http://localhost:8103/musician/facebook/login");
            //params.put("redirect_uri", "http://localhost:8081/draft");
            params.put("redirect_uri", "http://com.fy.tdraft:/draft");
            String str = JSONObject.toJSONString(params);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7980));
            String response = HttpUtil.createPost("https://graph.facebook.com/v3.3/oauth/access_token").body(str).setProxy(proxy).execute().body();
            JSONObject json = JSONObject.parseObject(response);
            System.out.println(json.toJSONString());
            if(json.getString("access_token")!=null){
                String accesstoken = json.getString("access_token");
               // System.out.println(json.getString("access_token"));
                result =  getUserInfo(accesstoken);
                return result;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public JSONObject getUserInfo(String accessToken){
        //利用代理时使用
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7980));
        Map<String, String> headers = new HashMap<>();
        String result = HttpUtil.createGet("https://graph.facebook.com/me?fields=email").header("Authorization", "Bearer " + accessToken).setProxy(proxy).execute().body();
        JSONObject json = JSONObject.parseObject(result);
        System.out.println(json.toJSONString());
        return json;
    }

    @GetMapping("/facebook/getUserInfo")
    public void getCode(@RequestParam("accessToken") String accessToken) {
        getUserInfo(accessToken);
    }

}


