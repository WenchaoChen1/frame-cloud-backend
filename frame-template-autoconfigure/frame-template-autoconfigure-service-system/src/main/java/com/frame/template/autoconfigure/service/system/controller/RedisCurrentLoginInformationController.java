package com.frame.template.autoconfigure.service.system.controller;

import com.frame.template.autoconfigure.service.system.service.RedisCurrentLoginInformationService;
import com.frame.template.common.redis.currentLoginInformation.CurrentLoginInformation;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformationInput;
import com.gstdev.cloud.base.definition.domain.Result;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/current-login-information")
public class RedisCurrentLoginInformationController {
    @Resource
    private RedisCurrentLoginInformationService redisCurrentLoginInformationService;

    /**
     * 存储当前用户选择的账号
     *
     * @return
     */
    @Operation(summary = "")
    @PostMapping("/add-by-token-current-login-information")
    public Result<Object> addByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput) {
        try {
            return redisCurrentLoginInformationService.addByTokenCurrentLoginInformation(redisCurrentLoginInformationInput);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
    }
    @Operation(summary = "")
    @PostMapping("/add-aby-token-current-login-information")
    public Result<Object> addByTokenCurrentaLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput) {
        try {
            return redisCurrentLoginInformationService.addByTokenCurrentLoginInformation(redisCurrentLoginInformationInput);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(e.getMessage());
        }
    }

    /**
     * 更新存储当前用户选择的账号
     *
     * @return
     */
    @Operation(summary = "")
    @PostMapping("/update-by-token-current-login-information")
    public Result<Object> updateByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput) {
        try {
            return redisCurrentLoginInformationService.updateByTokenCurrentLoginInformation(redisCurrentLoginInformationInput);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("Delete failed");
        }
    }

    /**
     * 更新存储当前用户选择的账号
     *
     * @return
     */
    @Operation(summary = "")
    @DeleteMapping("/delete-by-token-current-login-information")
    public Result<Object> deleteByTokenCurrentLoginInformation() {
        try {
            return redisCurrentLoginInformationService.deleteByTokenCurrentLoginInformation();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("Delete failed");
        }
    }

    /**
     * 获取当前用户信息
     * 如果前段展示的时候可以使用它
     * 后端保存的时候建议只取account-id
     *
     * @return
     */
    @Operation(summary = "")
    @GetMapping("/get-current-login-information")
    public Result<CurrentLoginInformation> getCurrentLoginInformation() {
        try {
            return redisCurrentLoginInformationService.getCurrentLoginInformation();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("Delete failed");
        }
    }
}
