package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.annotation.AccessLimited;
import com.gstdev.cloud.rest.core.annotation.Idempotent;
import com.gstdev.cloud.rest.core.controller.ResultController;
import com.gstdev.cloud.service.system.domain.entity.SysBusinessPermission;
import com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission.BusinessPermissionManageQO;
import com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission.BusinessPermissionManageTreeVo;
import com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission.InsertBusinessPermissionManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission.UpdateBusinessPermissionManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysPermission.PermissionManageDetailVo;
import com.gstdev.cloud.service.system.mapper.SysBusinessPermissionMapper;
import com.gstdev.cloud.service.system.service.SysBusinessPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Description: SysPermissionController </p>
 */

@RestController
@RequestMapping("/v1/business-permission")
public class SysBusinessPermissionController implements ResultController {

    @Resource
    private SysBusinessPermissionService sysBusinessPermissionService;

    @Resource
    private SysBusinessPermissionMapper sysBusinessPermissionMapper;

    public SysBusinessPermissionService getService() {
        return sysBusinessPermissionService;
    }

    public SysBusinessPermissionMapper getMapper() {
        return sysBusinessPermissionMapper;
    }

    // ********************************* BusinessPermission Manage *****************************************
    @Tags({
            @Tag(name = "Business Permission Manage"),
    })
    @AccessLimited
    @GetMapping("/get-business-permission-manage-tree")
    @Operation(summary = "get-business-permission-manage-tree")
    public Result<List<BusinessPermissionManageTreeVo>> getBusinessPermissionManageTree(BusinessPermissionManageQO businessPermissionManageQO, BasePage basePage) {
        List<SysBusinessPermission> byPage = this.getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, businessPermissionManageQO, criteriaBuilder));
        return this.result(this.getMapper().toBusinessPermissionManageTreeVoToTree(byPage));
    }


    @Tag(name = "Business Permission Manage")
    @GetMapping("/get-business-permission-manage-detail/{id}")
    @Operation(summary = "get-business-permission-manage-detail")
    public Result<PermissionManageDetailVo> getBusinessPermissionManageDetail(@PathVariable String id) {
        return result(getMapper().toBusinessPermissionManageDetailVo(getService().findById(id)));
    }

    @Tag(name = "Business Permission Manage")
    @Idempotent
    @Operation(summary = "insert-business-permission-manage")
    @PostMapping("/insert-business-permission-manage")
    public Result<String> insertAndUpdateBusinessPermissionManage(@RequestBody @Validated InsertBusinessPermissionManageIO insertBusinessPermissionManageIO) {
        this.getService().insertAndUpdate(getMapper().toEntity(insertBusinessPermissionManageIO));
        return Result.success();
    }

    @Tag(name = "Business Permission Manage")
    @PutMapping("/update-business-permission-manage")
    @Operation(summary = "update-business-permission-manage")
    public Result<String> updateBusinessPermissionManage(@RequestBody @Validated UpdateBusinessPermissionManageIO updateBusinessPermissionManageIO) {
        SysBusinessPermission sysBusinessPermission = this.getService().findById(updateBusinessPermissionManageIO.getId());
        getMapper().copy(updateBusinessPermissionManageIO, sysBusinessPermission);
        this.getService().insertAndUpdate(sysBusinessPermission);
        return Result.success();
    }


    @Tag(name = "Business Permission Manage")
    @Operation(summary = "delete-business-permission-manage")
    @DeleteMapping("/delete-business-permission-manage/{id}")
    public Result<String> deleteBusinessPermissionManage(@PathVariable String id) {
        this.getService().deleteById(id);
        return Result.success();
    }

    @Tag(name = "Business Permission Manage")
    @Operation(summary = "delete-all-business-permission-manage")
    @DeleteMapping("/delete-all-business-permission-manage")
    public Result<String> deleteAllBusinessPermissionManage(List<String> id) {
        this.getService().deleteAllById(id);
        return Result.success();
    }
//
//    @Tag(name = "Business Permission Manage")
//    @Operation(summary = "get-all-distinct-business-permission-type")
//    @GetMapping("/get-all-distinct-business-permission-type")
//    public Result<List<String>> getAllDistinctBusinessPermissionType() {
//        return result(getService().findDistinctBusinessPermissionTypes());
//    }

    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/

}
