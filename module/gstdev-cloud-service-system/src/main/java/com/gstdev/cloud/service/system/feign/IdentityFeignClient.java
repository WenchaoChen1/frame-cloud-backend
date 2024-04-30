package com.gstdev.cloud.service.system.feign;


import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.service.system.feign.vo.IdentitySaveDto;
import com.gstdev.cloud.service.system.feign.vo.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


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
    void deleteByUserId(@RequestParam("userId") String userId);

    @PostMapping("update-email")
    Result<UserDto> updateEmail(@RequestBody IdentitySaveDto identitySaveDto);


}
