package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.annotation.AccessLimited;
import com.gstdev.cloud.rest.core.annotation.Idempotent;
import com.gstdev.cloud.rest.core.controller.DtoController;
import com.gstdev.cloud.service.system.domain.base.SysPermission.SysPermissionDto;
import com.gstdev.cloud.service.system.domain.base.SysPermission.SysPermissionVo;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.InsertPermissionManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.PermissionManageDetailVo;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.PermissionManageQO;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.UpdatePermissionManageIO;
import com.gstdev.cloud.service.system.mapper.vo.SysPermissionMapper;
import com.gstdev.cloud.service.system.service.SysPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>Description: SysPermissionController </p>
 */

@RestController
@RequestMapping("/v1/permission")
public class SysPermissionController implements DtoController<SysPermission, String, SysPermissionDto> {

    @Resource
    private SysPermissionService sysPermissionService;

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public SysPermissionService getService() {
        return sysPermissionService;
    }

    public SysPermissionMapper getMapper() {
        return sysPermissionMapper;
    }

    // ********************************* Permission Manage *****************************************
    @Tag(name = "Permission Manage")
    @AccessLimited
    @GetMapping("/get-permission-manage-page")
    @Operation(summary = "get-permission-manage-page")
    public Result<Map<String, Object>> getPermissionManagePage(PermissionManageQO permissionManageQO, BasePage basePage) {
        Page<SysPermission> byPage = this.getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, permissionManageQO, criteriaBuilder), basePage);
        return this.result(this.getMapper().toPermissionManagePageVo(byPage));
    }

    @Tag(name = "Permission Manage")
    @GetMapping("/get-permission-manage-detail/{id}")
    @Operation(summary = "get-permission-manage-detail")
    public Result<PermissionManageDetailVo> getPermissionManageDetail(@PathVariable String id) {
        return result(getMapper().toPermissionManageDetailVo(getService().findById(id)));
    }

    @Tag(name = "Permission Manage")
    @Idempotent
    @Operation(summary = "insert-permission-manage", description = "接收JSON数据，转换为实体，进行保存或更新",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
        responses = {@ApiResponse(description = "已保存数据", content = @Content(mediaType = "application/json"))})
    @Parameters({
        @Parameter(name = "domain", required = true, description = "可转换为实体的json数据")
    })
    @PostMapping("/insert-permission-manage")
    public Result<SysPermissionVo> insertAndUpdatePermissionManage(@RequestBody @Validated InsertPermissionManageIO insertPermissionManageIO) {
        this.getService().insertAndUpdate(getMapper().toEntity(insertPermissionManageIO));
        return result();
    }

    @Tag(name = "Permission Manage")
    @PutMapping("/update-permission-manage")
    @Operation(summary = "update-permission-manage")
    public Result<SysPermissionVo> updatePermissionManage(@RequestBody @Validated UpdatePermissionManageIO updatePermissionManageIO) {
        SysPermission sysPermission = this.getService().findById(updatePermissionManageIO.getPermissionId());
        getMapper().copy(updatePermissionManageIO, sysPermission);
        this.getService().insertAndUpdate(sysPermission);
        return result();
    }

//    @Tag(name = "Permission Manage")
//    @Idempotent
//    @Operation(summary = "删除数据", description = "根据实体ID删除数据，以及相关联的关联数据",
//        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
//        responses = {@ApiResponse(description = "操作消息", content = @Content(mediaType = "application/json"))})
//    @Parameters({
//        @Parameter(name = "id", required = true, in = ParameterIn.PATH, description = "实体ID，@Id注解对应的实体属性")
//    })
//    @Override
//    @DeleteMapping("/{id}")
//    public Result<String> delete(@PathVariable String id) {
//        return DtoController.super.delete(id);
//    }

    @Tag(name = "Permission Manage")
    @Operation(summary = "delete-permission-manage")
    @DeleteMapping("/delete-permission-manage/{id}")
    public Result deletePermissionManage(@PathVariable String id) {
        return DtoController.super.delete(id);
    }

    @Tag(name = "Permission Manage")
    @Operation(summary = "delete-all-permission-manage")
    @DeleteMapping("/delete-all-permission-manage")
    public Result deleteAllPermissionManage(List<String> id) {
        this.getService().deleteAllById(id);
        return result();
    }

    @Tag(name = "Permission Manage")
    @Operation(summary = "get-all-distinct-permission-type",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
        responses = {@ApiResponse(description = "操作消息", content = @Content(mediaType = "application/json"))})
    @GetMapping("/get-all-distinct-permission-type")
    public Result<List<String>> getAllDistinctPermissionType() {
        return result(getService().findDistinctPermissionTypes());
    }

    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/

}
