package com.frame.template.gateway.filter;

import com.frame.template.gateway.properties.GatewaySecurityProperties;
import com.frame.template.gateway.utils.WebFluxUtils;
import com.gstdev.cloud.commons.ass.core.utils.http.HeaderUtils;
import com.gstdev.cloud.commons.ass.definition.constants.BaseConstants;
import com.gstdev.cloud.commons.ass.definition.constants.ErrorCodes;
import com.gstdev.cloud.commons.ass.definition.domain.Result;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <p> Description : GlobalCertificationFilter </p>
 *
 * @author : cc
 * @date : 2020/3/4 18:36
 */
@Component
public class GlobalCertificationFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(GlobalCertificationFilter.class);
    @Resource
    private GatewaySecurityProperties gatewaySecurityProperties;

    /**
     * 说明：目前所有请求均是通过Gateway进行访问。
     * /oauth/check_token，是比较特殊的地址，不是使用token的方式进行鉴权。
     * 虽然目前使用的是“permitAll”的方式，不够安全。但是不管什么情况，在Gateway这一端，不应该进行拦截。
     * 后续可以根据IP，以及OAuth2鉴权的方式进行安全控制。
     *
     * @param exchange exchange
     * @param chain    chain
     * @return 无
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.debug("[Gstdev Cloud] |- Gateway Global Certification Filter in use!");

        // 1.检查是否是免登陆连接
        String url = exchange.getRequest().getURI().getPath();
        List<String> whiteList = gatewaySecurityProperties.getWhiteList();

        if (WebFluxUtils.isPathMatch(whiteList, url)) {
            return chain.filter(exchange);
        }

        // 2.外部进入的请求，如果包含 X_HERODOTUS_FROM_IN 请求头，认为是非法请求，直接拦截。X_HERODOTUS_FROM_IN 只能用于内部 Feign 间忽略权限使用
        String fromIn = exchange.getRequest().getHeaders().getFirst(HeaderUtils.X_HERODOTUS_FROM_IN);
        if (ObjectUtils.isNotEmpty(fromIn)) {
            log.warn("[Gstdev Cloud] |- Illegal request to disable access!");
            return WebFluxUtils.writeJsonResponse(exchange.getResponse(), new Result<String>().type(ErrorCodes.ACCESS_DENIED).status(HttpStatus.SC_FORBIDDEN));
        }

        String webSocketToken = exchange.getRequest().getHeaders().getFirst(com.google.common.net.HttpHeaders.SEC_WEBSOCKET_PROTOCOL);
        if (StringUtils.isNotBlank(webSocketToken) && StringUtils.endsWith(webSocketToken, ".stomp")) {
            return chain.filter(exchange);
        }

        // 3.非免登陆地址，获取token 检查token，如果为空，或者不是 Bearer XXX形式，则认为未授权。
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (!isTokenWellFormed(token)) {
            log.warn("[Gstdev Cloud] |- Token is not Well Formed!");
            return WebFluxUtils.writeJsonResponse(exchange.getResponse(), new Result<String>().type(ErrorCodes.ACCESS_DENIED).status(HttpStatus.SC_UNAUTHORIZED));
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return FilterOrder.GLOBAL_CERTIFICATION_FILTER_ORDER;
    }

    private boolean isTokenWellFormed(String token) {
        return !StringUtils.isBlank(token) && !StringUtils.containsOnly(token, BaseConstants.BEARER_TOKEN);
    }
}
