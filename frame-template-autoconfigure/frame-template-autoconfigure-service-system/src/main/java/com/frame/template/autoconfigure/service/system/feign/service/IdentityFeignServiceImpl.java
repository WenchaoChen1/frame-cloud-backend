package com.frame.template.autoconfigure.service.system.feign.service;


import com.frame.template.autoconfigure.service.system.feign.IdentityFeignClient;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.service.system.feign.service.IdentityFeignService;
import com.gstdev.cloud.service.system.feign.vo.IdentitySaveDto;
import com.gstdev.cloud.service.system.feign.vo.UserDto;
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
public class IdentityFeignServiceImpl implements IdentityFeignService {

    private final IdentityFeignClient identityFeignClient;
    @Override
    public UserDto save(IdentitySaveDto identitySaveDto) {
        Result<UserDto> result = identityFeignClient.save(identitySaveDto);
        if (null == result) {
//            throw new RuntimeException(500, "Call feign interface ---user.save exception --null");
            throw new RuntimeException();
        }
        if (!result.getSuccess()) {
//            throw new CommonException(500, "Call the feign interface --- user.save exception ---" + result.getMessage());
            throw new RuntimeException();
        }
        UserDto dto = result.getData();
        return dto;
    }

//    public void deleteByUserId(String userId) {
//        identityFeignClient.deleteByUserId(userId);
//    }
//
//    public Result<UserDto> updateEmail(IdentitySaveDto identitySaveDto) {
//        return identityFeignClient.updateEmail(identitySaveDto);
//    }

//    public Object login(String username, String password, Boolean rememberMe) {
//        return identityClient.login("Basic cGFzc3dvcmQtY2xpZW50OmJsYWNrMTIz", username, password, rememberMe, "password");
//    }

}
