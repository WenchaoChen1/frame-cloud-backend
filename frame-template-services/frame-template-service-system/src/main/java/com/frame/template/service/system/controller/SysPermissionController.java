package com.frame.template.service.system.controller;

import com.frame.template.service.system.pojo.domain.SysPermission;
import com.frame.template.service.system.service.SysPermissionService;
import com.gstdev.cloud.rest.core.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Description: SysPermissionController </p>

 */
@RestController
@RequestMapping("/security/permission")
@Tags({
        @Tag(name = "用户安全管理接口"),
        @Tag(name = "系统权限管理接口")
})
public class SysPermissionController extends BaseController<SysPermission, String,SysPermissionService> {

    private  SysPermissionService sysPermissionService;

    public SysPermissionController(SysPermissionService sysPermissionService) {
        super(sysPermissionService);
    }

//    @AccessLimited
//    @Operation(summary = "获取全部权限", description = "获取全部权限数据列表",
//            responses = {
//                    @ApiResponse(description = "全部数据列表", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
//                    @ApiResponse(responseCode = "204", description = "查询成功，未查到数据"),
//                    @ApiResponse(responseCode = "500", description = "查询失败")
//            })
//    @GetMapping("/list")
//    public Result<List<SysPermission>> findAll() {
//        List<SysPermission> sysAuthorities = sysPermissionService.findAll();
//        return result(sysAuthorities);
//    }
}
