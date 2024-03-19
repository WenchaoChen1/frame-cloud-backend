package com.frame.template.service.demo.feign.service;

import com.frame.template.common.exception.CommonException;
import com.frame.template.common.feign.ApiResult;
import com.frame.template.service.demo.feign.client.IdentityFeignClient;
import com.frame.template.service.demo.feign.vo.IdentitySaveDto;
import com.frame.template.service.demo.feign.vo.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: zcy
 * @date: 2022/12/7
 * @description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class IdentityFeignService {
  private final IdentityFeignClient identityFeignClient;

  public UserDto save(IdentitySaveDto identitySaveDto) {
    log.debug("调用feign接口开始---user.save");
    ApiResult<UserDto> result = identityFeignClient.save(identitySaveDto);
//    log.debug("Feign调用 areaClient.getAreaName end:{}", JSON.toJSONString(result));
    if (null == result) {
      throw new CommonException(500, "调用feign接口---user.save  异常 --null");
    }
    if (!result.getSuccess()) {
      throw new CommonException(500, "调用feign接口---user.save  异常--" + result.getMessage());
    }
    UserDto dto = result.getData();
    return dto;
  }
}
