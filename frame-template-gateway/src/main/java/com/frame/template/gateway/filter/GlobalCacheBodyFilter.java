//package com.frame.template.gateway.filter;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferUtils;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
///**
// * <p>Description: Request Body 缓存过滤器 </p>
// * <p>
// * 这个过滤器解决body不能重复读的问题，为后续的GlobalFilter重写post|put请求的body做准备
// *
// * @author : cc
// * @date : 2021/9/1 16:07
// */
//@Component
//public class GlobalCacheBodyFilter implements GlobalFilter, Ordered {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        HttpMethod method = exchange.getRequest().getMethod();
//        HttpHeaders headers = exchange.getRequest().getHeaders();
//        String contentType = headers.getFirst(HttpHeaders.CONTENT_TYPE);
//        if (method == HttpMethod.POST || method == HttpMethod.PUT) {
//            if (MediaType.APPLICATION_FORM_URLENCODED_VALUE.equalsIgnoreCase(contentType)
//                    || MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType)
//                    || MediaType.APPLICATION_JSON_UTF8_VALUE.equals(contentType)) {
//                return DataBufferUtils.join(exchange.getRequest().getBody())
//                        .flatMap(dataBuffer -> {
//                            byte[] bytes = new byte[dataBuffer.readableByteCount()];
//                            dataBuffer.read(bytes);
//                            // 释放堆外内存
//                            DataBufferUtils.release(dataBuffer);
//                            ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
//                                @Override
//                                public Flux<DataBuffer> getBody() {
//                                    return Flux.defer(() -> {
//                                        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
//                                        DataBufferUtils.retain(buffer);
//                                        return Mono.just(buffer);
//                                    });
//                                }
//
//                                @Override
//                                public HttpHeaders getHeaders() {
//                                    return headers;
//                                }
//                            };
//                            return chain.filter(exchange.mutate().request(mutatedRequest).build());
//                        });
//            }
//
//        }
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return FilterOrder.GLOBAL_CACHE_BODY_FILTER_ORDER;
//    }
//}
