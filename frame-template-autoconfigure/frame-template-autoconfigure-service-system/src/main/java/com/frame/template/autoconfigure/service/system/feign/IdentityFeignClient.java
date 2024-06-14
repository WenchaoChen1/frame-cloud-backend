package com.frame.template.autoconfigure.service.system.feign;


import com.frame.template.common.constant.ServiceConstants;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.service.system.feign.vo.IdentitySaveDto;
import com.gstdev.cloud.service.system.feign.vo.UserDto;
import com.gstdev.cloud.springframework.openfeign.annotation.Inner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


//@FeignClient(contextId = "identityClient", name = ServiceConstants.SERVICE_NAME_IDENTITY, path = "/user")
@FeignClient(contextId = "identityClient", value = ServiceConstants.SERVICE_NAME_IDENTITY)
//@FeignClient(contextId = "identityClient", name = ServiceConstants.SERVICE_NAME_IDENTITY, url = "http://127.0.0.1:8201/" + ServiceConstants.SERVICE_NAME_IDENTITY)
public interface IdentityFeignClient {


    @Inner
    @PostMapping("/user/save")
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
