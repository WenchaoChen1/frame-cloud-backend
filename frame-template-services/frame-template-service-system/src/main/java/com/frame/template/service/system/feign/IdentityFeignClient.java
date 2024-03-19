package com.frame.template.service.system.feign;


import com.gstdev.cloud.commons.web.Result;
import com.frame.template.common.constant.ServiceConstants;
import com.frame.template.service.system.feign.vo.IdentitySaveDto;
import com.frame.template.service.system.feign.vo.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhucy
 */
@FeignClient(contextId = "identityClient", name = ServiceConstants.SERVICE_NAME_IDENTITY, path = "/user")
public interface IdentityFeignClient {
  @GetMapping(value = "/a")
  String a();


  @PostMapping("save")
  Result<UserDto> save(@RequestBody IdentitySaveDto saveDto);

  @GetMapping("get_security_user_id")
  Result<String> getSecurityUserId();

  @GetMapping("get_user_id")
  Result<String> getUserId();

  @GetMapping("get_user_id")
  Result<String> getUserIdHerder(@RequestHeader("authorization") String authorization);

  @DeleteMapping("delete-by-userId/{userId}")
  void deleteByUserId(@RequestParam("userId")  String userId);

  @PostMapping("update-email")
  Result<UserDto> updateEmail(@RequestBody IdentitySaveDto identitySaveDto);


}
