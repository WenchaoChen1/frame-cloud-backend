package com.gstdev.template.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignConfig {

  private static final String TOKEN_HEADER = "authorization";

  @Bean
  public RequestInterceptor requestInterceptor() {
    return new RequestInterceptor() {
      @Override
      public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
          //获取请求的token信息
          String token = requestAttributes.getRequest().getHeader(TOKEN_HEADER);
          //同步到请求中
          requestTemplate.header(TOKEN_HEADER, token);
          return;
        }
      }
    };
  }

}
