package com.frame.template.service.demo.feign.client;


import com.frame.template.common.constant.ServiceConstants;
import com.frame.template.common.feign.ApiResult;
import com.frame.template.service.demo.feign.vo.IdentitySaveDto;
import com.frame.template.service.demo.feign.vo.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author zhucy
 */
@FeignClient(contextId = "identityClient", name = ServiceConstants.SERVICE_NAME_IDENTITY, path = "/user")
public interface IdentityFeignClient {
  @GetMapping(value = "/a")
  String a();

  /**
   * 同步保存到identity
   *
   * @param saveDto
   */
  @PostMapping()
  ApiResult<UserDto> save(IdentitySaveDto saveDto);
}
