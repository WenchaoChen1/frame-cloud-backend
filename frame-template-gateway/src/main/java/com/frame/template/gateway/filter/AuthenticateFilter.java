package com.frame.template.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frame.template.gateway.dto.TokenDTO;
import com.frame.template.gateway.utils.PathMatchUtils;
import com.gstdev.cloud.commons.domain.Result;

import com.frame.template.gateway.service.WebClientAuthenticateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

/**
 * 接口权限校验校验
 */
@Component
@Order
@RefreshScope
@Slf4j
public class AuthenticateFilter implements GlobalFilter, Ordered {


  //token的header名字
  private static final String TOKEN_NAME = "Authentication";
  //tokencheck标识
  private static final String TOKEN_CHECK = "token-check";
  @Autowired
  private WebClientAuthenticateService authenticateService;
  @Autowired
  private IgnoreWhiteProperties ignoreWhite;

  /**
   * 设置webflux模型响应
   *
   * @param response ServerHttpResponse
   * @param code     响应状态码
   * @param value    响应内容
   * @return Mono<Void>
   */
  public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, Object value, int code) {
    return webFluxResponseWriter(response, HttpStatus.OK, value, code);
  }

  /**
   * 设置webflux模型响应
   *
   * @param response ServerHttpResponse
   * @param status   http状态码
   * @param code     响应状态码
   * @param value    响应内容
   * @return Mono<Void>
   */
  public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, HttpStatus status, Object value, int code) {
    return webFluxResponseWriter(response, MediaType.APPLICATION_JSON_VALUE, status, value, code);
  }

  /**
   * 设置webflux模型响应
   *
   * @param response    ServerHttpResponse
   * @param contentType content-type
   * @param status      http状态码
   * @param code        响应状态码
   * @param value       响应内容
   * @return Mono<Void>
   */
  public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, String contentType, HttpStatus status, Object value, int code) {
    response.setStatusCode(status);
    response.getHeaders().add(HttpHeaders.CONTENT_TYPE, contentType);
    Result result = Result.failure(code + "", value.toString());
    DataBuffer dataBuffer = response.bufferFactory().wrap(JSON.toJSONString(result).getBytes());
    return response.writeWith(Mono.just(dataBuffer));
  }

//  @Override
//  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//
//    //先检查是否需要鉴权
//    String path = exchange.getRequest().getPath().value();
//    log.debug("网关--------------" + path);
//
//    if (log.isDebugEnabled()) {
//      log.debug("请求的路径为:{}", path);
//    }
//
//    /**
//     * 使用动态规划算法匹配*路径
//     */
//    for (String whitesPath : ignoreWhite.getWhites()) {
//      if (PathMatchUtils.isMatch(path, whitesPath)) {
//        if (log.isDebugEnabled()) {
//          log.debug("请求的路径'{}'不需要进行鉴权", path);
//          log.debug("白名单匹配规则为:{}", whitesPath);
//        }
//        return chain.filter(exchange);
//      }
//    }
//
//    //检查token是否已check过
//    List<String> tokenFlags = exchange.getRequest().getHeaders().get(TOKEN_CHECK);
//    boolean anyMatch = CollectionUtils.isEmpty(tokenFlags) ? false : tokenFlags.stream().anyMatch(tokenFlag -> StringUtils.equalsIgnoreCase(tokenFlag, "true"));
//    if (anyMatch) {
//      return chain.filter(exchange);
//    }
//
//    //检查token
//    List<String> tokens = exchange.getRequest().getHeaders().get(TOKEN_NAME);
//    if (tokens == null || tokens.isEmpty()) {
//      String errorMsg = "请求需要进行鉴权，请求header中的" + TOKEN_NAME + "为null或者空值";
//      if (log.isDebugEnabled()) {
//        log.debug(errorMsg);
//      }
//      return setUnauthorizedResponse(exchange, "令牌不能为空");
//    }
//    String token = tokens.get(0);
//    TokenDTO tokenDto = new TokenDTO();
//    tokenDto.setToken(token);
//    tokenDto.setTokenType(TOKEN_NAME);
//    if (log.isDebugEnabled()) {
//      log.debug("当前token为-------------------：" + token);
//    }
//    return authenticateService.checkToken(tokenDto).onErrorResume(e -> {
//      e.printStackTrace();
//      log.error("访问校验接口出现错误:{}", e.getLocalizedMessage(), e);
//
//      exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
//      return Mono.just(Result.failure("服务异常"));
//    }).doOnSuccess(commonResponse -> {
//      if (log.isDebugEnabled()) {
//        log.debug("请求checkToken接口成功，返回：{}", commonResponse);
//      }
//    }).map(commonResponse -> {
//      if (log.isDebugEnabled()) {
//        log.debug("checkToken验证结果:{}", commonResponse);
//      }
//      if (commonResponse.getCode().equals("200")) {
//        if (log.isDebugEnabled()) {
//          log.debug("checkToken校验成功,token:{}", tokenDto.getToken());
//        }
//
//
//        exchange.getRequest().mutate().header(TOKEN_NAME, tokenDto.getToken());
//
//
//        return chain.filter(exchange);
//      } else {
//        if (log.isDebugEnabled()) {
//          log.debug("checkToken验证失败，token:{}", tokenDto.getToken());
//        }
//
//        return getVoidMono(exchange, commonResponse);
//      }
//    }).flatMap(Function.identity());
//  }

  private Mono<Void> getVoidMono(ServerWebExchange exchange, Result AUTH_FAIL) {
    return exchange.getResponse().writeWith(Mono.create(sink -> {
      ObjectMapper mapper = new ObjectMapper();
      try {
        byte[] bytes = mapper.writeValueAsBytes(AUTH_FAIL);
        sink.success(exchange.getResponse().bufferFactory().wrap(bytes));
      } catch (JsonProcessingException jsonProcessingException) {
        sink.error(jsonProcessingException);
      }
    }));
  }

  @Override
  public int getOrder() {
    return Integer.MIN_VALUE + 2;
  }

  /**
   * 设置没有权限的返回
   *
   * @param exchange
   * @param msg
   * @return
   */
  private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, String msg) {
    log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());
    ServerHttpResponse response = exchange.getResponse();
    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
    return webFluxResponseWriter(response, msg, 4000);
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    return null;
  }
}
