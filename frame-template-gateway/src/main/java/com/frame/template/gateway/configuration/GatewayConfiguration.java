//package com.frame.template.gateway.configuration;
//
//import com.frame.template.gateway.handler.RefreshRoutesListener;
//import com.gstdev.cloud.commons.ass.core.annotation.ConditionalOnSwaggerEnabled;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springdoc.core.properties.SwaggerUiConfigParameters;
//import org.springdoc.core.properties.SwaggerUiConfigProperties;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.web.cors.reactive.CorsUtils;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
///**
// * <p>Description: Gateway 服务网关配置 </p>
// *
// * @author : cc
// * @date : 2021/11/4 11:46
// */
//@Configuration(proxyBeanMethods = false)
//public class GatewayConfiguration {
//
//    private static final Logger log = LoggerFactory.getLogger(GatewayConfiguration.class);
//
//    private static final String MAX_AGE = "18000L";
//
//    /**
//     * Gateway 跨域处理
//     *
//     * @return WebFilter
//     */
//    @Bean
//    public WebFilter corsFilter() {
//        return (ServerWebExchange ctx, WebFilterChain chain) -> {
//            ServerHttpRequest request = ctx.getRequest();
//            if (CorsUtils.isCorsRequest(request)) {
//                HttpHeaders requestHeaders = request.getHeaders();
//                ServerHttpResponse response = ctx.getResponse();
//                HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
//                HttpHeaders headers = response.getHeaders();
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
//                headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders.getAccessControlRequestHeaders());
//                if (requestMethod != null) {
//                    headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
//                }
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
//                headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
//                headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
//                if (request.getMethod() == HttpMethod.OPTIONS) {
//                    response.setStatusCode(HttpStatus.OK);
//                    return Mono.empty();
//                }
//
//            }
//            return chain.filter(ctx);
//        };
//    }
//
////    @Configuration(proxyBeanMethods = false)
//////    @ConditionalOnSwaggerEnabled
////    static class GatewaySwaggerConfiguration {
////
////      // 定义一个 Bean，用于监听路由刷新事件
////      @Bean
////      public RefreshRoutesListener refreshRoutesListener(RouteLocator routeLocator, SwaggerUiConfigParameters swaggerUiConfigParameters, SwaggerUiConfigProperties swaggerUiConfigProperties) {
////        // 创建 RefreshRoutesListener 实例
////        RefreshRoutesListener refreshRoutesListener = new RefreshRoutesListener();
////        // 设置路由定位器
////        refreshRoutesListener.setRouteLocator(routeLocator);
////        // 设置 Swagger UI 配置参数
////        refreshRoutesListener.setSwaggerUiConfigParameters(swaggerUiConfigParameters);
////        // 设置 Swagger UI 配置属性
////        refreshRoutesListener.setSwaggerUiConfigProperties(swaggerUiConfigProperties);
////        // 记录日志
////        log.trace("[Gstdev Cloud] |- Bean [Refresh Routes Listener] in AliyunScanConfiguration Auto Configure.");
////        // 返回 RefreshRoutesListener 实例
////        return refreshRoutesListener;
////      }
////    }
//}
