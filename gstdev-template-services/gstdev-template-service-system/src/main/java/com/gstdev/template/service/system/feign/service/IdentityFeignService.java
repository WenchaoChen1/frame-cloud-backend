package com.gstdev.template.service.system.feign.service;

import com.gstdev.cloud.commons.web.Result;
import com.gstdev.template.common.exception.CommonException;
import com.gstdev.template.service.system.feign.IdentityClient;
import com.gstdev.template.service.system.feign.IdentityFeignClient;
import com.gstdev.template.service.system.feign.vo.IdentitySaveDto;
import com.gstdev.template.service.system.feign.vo.UserDto;
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
  private final IdentityClient identityClient;
  public UserDto save(IdentitySaveDto identitySaveDto) {
    Result<UserDto> result = identityFeignClient.save(identitySaveDto);
    if (null == result) {
      throw new CommonException(500, "Call feign interface ---user.save exception --null");
    }
    if (!result.getSuccess()) {
      throw new CommonException(500, "Call the feign interface --- user.save exception ---" + result.getMessage());
    }
    UserDto dto = result.getData();
    return dto;
  }

  public void deleteByUserId( String userId) {
    identityFeignClient.deleteByUserId(userId);
  }

  public Result<UserDto> updateEmail(IdentitySaveDto identitySaveDto){
    return identityFeignClient.updateEmail(identitySaveDto);
  }

  public Object login(String username,String password,Boolean rememberMe){
    return identityClient.login("Basic cGFzc3dvcmQtY2xpZW50OmJsYWNrMTIz",username,password,rememberMe,"password");
  }

}
