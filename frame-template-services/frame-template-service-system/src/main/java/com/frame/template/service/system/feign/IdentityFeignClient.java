package com.frame.template.service.system.feign;


import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.service.system.feign.vo.IdentitySaveDto;
import com.gstdev.cloud.service.system.feign.vo.UserDto;
import com.gstdev.cloud.springframework.openfeign.annotation.Inner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(contextId = "identityClient", name = ServiceConstants.SERVICE_NAME_IDENTITY, path = "/user")
public interface IdentityFeignClient {


    @Inner
    @PostMapping("save")
    Result<UserDto> save(@RequestBody IdentitySaveDto saveDto);






//    @GetMapping(value = "/a")
//    String a();
//    @GetMapping("get_security_user_id")
//    Result<String> getSecurityUserId();
//
//    @GetMapping("get_user_id")
//    Result<String> getUserId();
//
//    @GetMapping("get_user_id")
//    Result<String> getUserIdHerder(@RequestHeader("authorization") String authorization);
//
//    @DeleteMapping("delete-by-userId/{userId}")
//    void deleteByUserId(@RequestParam("userId") String userId);
//
//    @PostMapping("update-email")
//    Result<UserDto> updateEmail(@RequestBody IdentitySaveDto identitySaveDto);


}
