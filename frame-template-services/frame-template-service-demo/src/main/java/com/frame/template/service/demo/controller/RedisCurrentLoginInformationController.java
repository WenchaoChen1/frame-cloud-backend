package com.frame.template.service.demo.controller;

import com.frame.template.common.redis.currentLoginInformation.CurrentLoginInformation;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformationInput;
import com.frame.template.service.demo.service.RedisCurrentLoginInformationService;
import com.gstdev.cloud.base.definition.domain.Result;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/current_login_information")
public class RedisCurrentLoginInformationController {
    @Resource
    private RedisCurrentLoginInformationService redisCurrentLoginInformationService;

    /**
     * 存储当前用户选择的账号
     *
     * @return
     */
    @Operation(summary = "")
    @PostMapping("/add_by_token_current_login_information")
    public Result<Object> addByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput) {
        try {
            return redisCurrentLoginInformationService.addByTokenCurrentLoginInformation(redisCurrentLoginInformationInput);
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
    @PostMapping("/update_by_token_current_login_information")
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
    @DeleteMapping("/delete_by_token_current_login_information")
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
     * 后端保存的时候建议只取account_id
     *
     * @return
     */
    @Operation(summary = "")
    @DeleteMapping("/get_current_login_information")
    public Result<CurrentLoginInformation> getCurrentLoginInformation() {
        try {
            return redisCurrentLoginInformationService.getCurrentLoginInformation();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("Delete failed");
        }
    }
}
