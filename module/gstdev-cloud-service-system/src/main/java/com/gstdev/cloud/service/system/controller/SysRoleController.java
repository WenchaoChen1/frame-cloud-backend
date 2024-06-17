package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.service.system.mapper.vo.SysRoleMapper;
import com.gstdev.cloud.service.system.domain.base.role.*;
import com.gstdev.cloud.service.system.domain.entity.SysRole;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.service.system.domain.pojo.sysRole.InsertRoleManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysRole.UpdateRoleManageIO;
import com.gstdev.cloud.service.system.service.SysRoleService;
import com.gstdev.cloud.rest.core.controller.TreeController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.List;

//@ResponseBody
@RestController
@RequestMapping("/v1/role")
public class SysRoleController implements TreeController<SysRole, String, RoleVo, RoleDto, RoleInsertInput, RoleUpdateInput, RolePageQueryCriteria, RoleFindAllByQueryCriteria> {

    @Resource
    private SysRoleService roleService;

    @Resource
    private SysRoleMapper roleVoMapper;

//    public SysRoleController(SysRoleService roleService, RoleVoMapper roleVoMapper) {
//        this.roleService = roleService;
//        this.roleVoMapper = roleVoMapper;
//    }

    @Override
    public SysRoleService getService() {
        return roleService;
    }

    @Override
    public SysRoleMapper getMapper() {
        return roleVoMapper;
    }


    @GetMapping("/get-by-id")
    @Operation(summary = "根据id获取实体数据")
    public Result<RoleVo> getById(String id) {
        return findByIdToResult(id);
    }

//    @PostMapping
//    @Operation(summary = "新增一条数据")
//    public Result<RoleVo> insert(@RequestBody RoleInsertInput roleInsertInput) {
//        return insertToResult(roleInsertInput);
//    }
//
//    @PutMapping
//    @Operation(summary = "修改一条数据")
//    public Result<RoleVo> update(@RequestBody RoleUpdateInput roleUpdateInput) {
//        return updateToResult(roleUpdateInput);
//    }

    @Operation(summary = "")
    @DeleteMapping
    public Result<RoleVo> deleteById(String id) {
        return deleteByIdToResult(id);
    }

    @GetMapping("/get-all-by-role-id")
    @Operation(summary = "获取指定角色的所有菜单，返回id")
    public Result<List<String>> getAllByRoleId(@RequestParam("roleId") String roleId) {
        return getService().getAllByRoleId(roleId);
    }

    @PostMapping("/insertRoleMenu")
    @Operation(summary = "insertSave")
    public Result<String> insertRoleMenu(@RequestBody RoleInsertInput roleInsertInput) {
        return getService().insertRoleMenu(roleInsertInput);
    }


    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/

    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/

    // ********************************* Role Manage *****************************************

    @GetMapping("/get-role-manage-tree")
    @Operation(summary = "根据筛选获取所有角色，返回树状结构")
    public Result<List<RoleVo>> getRoleManageTree(RoleFindAllByQueryCriteria roleFindAllByQueryCriteria) {
        return findAllByQueryCriteriaToResultToTree(roleFindAllByQueryCriteria);
    }

    @GetMapping("/get-role-manage-detail/{id}")
    @Operation(summary = "get-role-manage-detail")
    public Result<RoleVo> getRoleManageDetail(@PathVariable String id) {
        return findByIdToResult(id);
    }

    @PostMapping("/insert-role-manage")
    @Operation(summary = "新增一条数据")
    public Result insertRoleManage(@RequestBody @Validated InsertRoleManageIO insertRoleManageIO) {
        this.getService().insertAndUpdate(roleVoMapper.toEntity(insertRoleManageIO));
        return result();
    }

    @PutMapping("/update-role-manage")
    @Operation(summary = "新增一条数据")
    public Result updateRoleManage(@RequestBody @Validated UpdateRoleManageIO updateRoleManageIO) {
        SysRole sysRole = this.getService().findById(updateRoleManageIO.getId());
        roleVoMapper.copy(updateRoleManageIO, sysRole);
        this.getService().insertAndUpdate(sysRole);
        return result();
    }


    @Operation(summary = "删除一条数据")
    @DeleteMapping("delete-role-manage/{id}")
    public Result deleteRoleManage(@PathVariable String id) {
        return deleteByIdToResult(id);
    }

    @Operation(summary = "删除多条数据")
    @DeleteMapping("delete-all-role-manage")
    public Result deleteAllRoleManage(List<String> id) {
        return deleteAllByIdToResult(id);
    }
    /*------------------------------------------ 以上是系统访问控制 --------------------------------------------*/
}

