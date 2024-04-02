//package com.frame.template.gateway.handler;
//
//import org.apache.commons.lang3.ObjectUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
//import org.springdoc.core.properties.SwaggerUiConfigParameters;
//import org.springdoc.core.properties.SwaggerUiConfigProperties;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.context.ApplicationListener;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * <p>Description: 服务变更监听 </p>
// *
// * @author : cc
// * @date : 2021/9/11 10:14
// */
//public class RefreshRoutesListener implements ApplicationListener<RefreshRoutesEvent> {
//
//    private static final Logger log = LoggerFactory.getLogger(RefreshRoutesListener.class);
//
//    public static final String API_URI = "/v3/api-docs";
//
//    /**
//     * 网关应用名称
//     */
//    @Value("${spring.application.name}")
//    private String self;
//    private RouteLocator routeLocator;
//    private SwaggerUiConfigParameters swaggerUiConfigParameters;
//    private SwaggerUiConfigProperties swaggerUiConfigProperties;
//
//    public void setRouteLocator(RouteLocator routeLocator) {
//        this.routeLocator = routeLocator;
//    }
//
//    public void setSwaggerUiConfigParameters(SwaggerUiConfigParameters swaggerUiConfigParameters) {
//        this.swaggerUiConfigParameters = swaggerUiConfigParameters;
//    }
//
//    public void setSwaggerUiConfigProperties(SwaggerUiConfigProperties swaggerUiConfigProperties) {
//        this.swaggerUiConfigProperties = swaggerUiConfigProperties;
//    }
//
//    @Override
//    public void onApplicationEvent(RefreshRoutesEvent refreshRoutesEvent) {
//
//        // 存储路由服务名的列表
//        List<String> routes = new ArrayList<>();
//        // 获取路由信息并过滤出 lb 协议的路由
//        routeLocator.getRoutes()
//            .filter(route -> route.getUri().getHost() != null && Objects.equals(route.getUri().getScheme(), "lb") && !self.equalsIgnoreCase(route.getUri().getHost()))
//            .subscribe(route -> routes.add(route.getUri().getHost()));
//
//        // 根据路由服务名创建 Swagger 文档的 URL 配置
//        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> swaggerUrls = routes.stream().map(this::createSwaggerUrl).collect(Collectors.toSet());
//
//        // 更新 Swagger 文档的 URL 配置
//        if (ObjectUtils.isNotEmpty(swaggerUiConfigParameters)) {
//            log.debug("[Gstdev Cloud] |- Services is Changed, update Urls");
//            swaggerUiConfigParameters.setUrls(swaggerUrls);
//            swaggerUiConfigProperties.setUrls(swaggerUrls);
//        }
//    }
//
//
//    // 根据服务名创建 Swagger 文档的 URL 配置
//    private AbstractSwaggerUiConfigProperties.SwaggerUrl createSwaggerUrl(String service) {
//
//        String data = API_URI;
//
//        // 根据服务名判断是否为 bpmn 服务，如果是则修改文档 URI
//        if (StringUtils.containsIgnoreCase(service, "bpmn")) {
//            data = "/openapi.json";
//        }
//
//        // 拼接 Swagger 文档的 URL
//        String url = "/" + service.toLowerCase() + data;
//
//        log.debug("[Gstdev Cloud] |- Create Swagger Url - Name: {}, Location {}.", service, url);
//
//        // 创建 SwaggerUrl 实例并设置 URL 和服务名
//        AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new AbstractSwaggerUiConfigProperties.SwaggerUrl();
//        swaggerUrl.setUrl(url);
//        swaggerUrl.setName(service);
//        return swaggerUrl;
//    }
//}
//
