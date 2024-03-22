//package com.spting.auth.config;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@Configuration
//public class RequestFeignConfig implements RequestInterceptor {
//
//  @Autowired
//  private TokenUtil tokenUtil;
//
//  @Override
//  public void apply(RequestTemplate requestTemplate) {
//    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//    if(attributes!=null&&attributes.getRequest()!=null){
//      HttpServletRequest request = attributes.getRequest();
//      requestTemplate.header("Authorization", request.getHeader("Authorization"));
//    }else{
//      requestTemplate.header("Authorization", "Bearer " + tokenUtil.getToken());
//    }
//  }
//}
