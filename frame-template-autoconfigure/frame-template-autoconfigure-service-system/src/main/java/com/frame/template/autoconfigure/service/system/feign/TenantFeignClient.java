//package com.frame.template.service.identity.feign;
//
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@FeignClient(contextId = "tenantClientId", name = ServiceConstants.SERVICE_NAME_TENANT)
//public interface TenantFeignClient {
//
//    @GetMapping(value = "/v1/tenant/findByTenantId")
//    List<TenantDto> findByTenantId(String tenantId);
//
//
//    @GetMapping(value = "/v1/tenant/get-by-id")
//    Result<TenantDto> findById(@RequestParam("id") String id);
//}
