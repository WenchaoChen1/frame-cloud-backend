package com.frame.template.service.demo.feign.client;


import com.frame.template.common.constant.ServiceConstants;
import com.frame.template.common.feign.ApiResult;
import com.frame.template.service.demo.feign.vo.TenantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(contextId = "tenantClientId", name = ServiceConstants.SERVICE_NAME_TENANT, path = "/tenant")
public interface TenantFeignClient {
  /**
   * 查询
   *
   * @param tenantId
   * @return
   */
  @GetMapping(value = "/findByTenantId")
  List<TenantDto> findByTenantId(String tenantId);

  /**
   * 主键查询
   *
   * @param tenantId
   * @return
   */
  @GetMapping
  ApiResult<TenantDto> findById(@RequestParam("id") String tenantId);
}
