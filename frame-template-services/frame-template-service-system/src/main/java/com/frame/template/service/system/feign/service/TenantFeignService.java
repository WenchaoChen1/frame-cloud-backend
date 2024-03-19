package com.frame.template.service.system.feign.service;

import com.frame.template.service.system.feign.TenantFeignClient;
import com.frame.template.service.system.feign.vo.TenantDto;
import com.gstdev.cloud.commons.web.Result;
import com.frame.template.common.exception.CommonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: zcy
 * @date: 2022/12/9
 * @description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TenantFeignService {
  private final TenantFeignClient tenantFeignClient;


  public TenantDto findById(String tenantId) {
    Result<TenantDto> tenantDtoApiResult = tenantFeignClient.findById(tenantId);
    if (null == tenantDtoApiResult || !tenantDtoApiResult.getSuccess()) {
      throw new CommonException(500, "Interface query exception");
    }
    return tenantDtoApiResult.getData();
  }
}
