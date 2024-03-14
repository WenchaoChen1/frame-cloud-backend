package com.gstdev.template.service.demo.feign.service;

import com.gstdev.template.common.exception.CommonException;
import com.gstdev.template.common.feign.ApiResult;
import com.gstdev.template.service.demo.feign.client.TenantFeignClient;
import com.gstdev.template.service.demo.feign.vo.TenantDto;
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

  /**
   * 查询租户信息
   *
   * @param tenantId
   * @return
   */
  public TenantDto findById(String tenantId) {
    ApiResult<TenantDto> tenantDtoApiResult = tenantFeignClient.findById(tenantId);
    if (null == tenantDtoApiResult || !tenantDtoApiResult.getSuccess()) {
      throw new CommonException(500, "接口查询异常");
    }
    return tenantDtoApiResult.getData();
  }
}
