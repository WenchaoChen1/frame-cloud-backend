package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.service.system.mapper.vo.SysPermissionVoMapper;
import com.gstdev.cloud.service.system.pojo.base.SysPermission.SysPermissionVo;
import com.gstdev.cloud.service.system.pojo.entity.SysPermission;
import com.gstdev.cloud.service.system.pojo.entity.SysPermission;
import com.gstdev.cloud.service.system.service.SysPermissionService;
import com.gstdev.cloud.service.system.service.SysPermissionService;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.rest.core.annotation.AccessLimited;
import com.gstdev.cloud.rest.core.controller.BaseController;
import com.gstdev.cloud.rest.core.controller.Controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>Description: SysPermissionController </p>
 */
@RestController
@RequestMapping("/v1/permission")
@Tags({
    @Tag(name = "用户安全管理接口"),
    @Tag(name = "系统权限管理接口")
})
public class SysPermissionController implements Controller<SysPermission, String, SysPermissionService> {

    @Resource
    private SysPermissionService sysPermissionService;

    private SysPermissionVoMapper SysPermissionVoMapper;

    @Override
    public SysPermissionService getService() {
        return sysPermissionService;
    }

    @AccessLimited
    @Operation(summary = "获取全部权限", description = "获取全部权限数据列表",
        responses = {
            @ApiResponse(description = "全部数据列表", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "204", description = "查询成功，未查到数据"),
            @ApiResponse(responseCode = "500", description = "查询失败")
        })
    @GetMapping("/page")
    public Result<Map<String, Object>> findByPage(Pageable pageable) {
        Page<SysPermission> byPage = getService().findByPage(pageable);
        Page<SysPermissionVo> SysPermissionVos = SysPermissionVoMapper.entityToVo(byPage);
        return Result.success((Map<String, Object>) SysPermissionVos);
    }
}