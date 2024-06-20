package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.controller.TreeController;
import com.gstdev.cloud.service.system.domain.base.role.*;
import com.gstdev.cloud.service.system.domain.entity.SysRole;
import com.gstdev.cloud.service.system.domain.pojo.sysRole.*;
import com.gstdev.cloud.service.system.mapper.vo.SysRoleMapper;
import com.gstdev.cloud.service.system.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@ResponseBody
@RestController
@RequestMapping("/v1/role")
public class SysRoleController implements TreeController<SysRole, String, RoleVo, RoleDto, RoleInsertInput, RoleUpdateInput, RolePageQueryCriteria, RoleFindAllByQueryCriteria> {

    @Resource
    private SysRoleService roleService;

    @Resource
    private SysRoleMapper roleVoMapper;
    @Override
    public SysRoleService getService() {
        return roleService;
    }

    @Override
    public SysRoleMapper getMapper() {
        return roleVoMapper;
    }

    // ********************************* Role Manage *****************************************
    @Tag(name = "Role Manage")
    @GetMapping("/get-role-manage-page")
//    @Operation(summary = "根据筛选获取所有角色")
    public  Result<Map<String, Object>> getRoleManagePage(RoleManagePageQO queryCriteria, BasePage basePage){
        Page<SysRole> byPage = this.getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder), basePage);
        return this.result(this.getMapper().toRoleManagePageVo(byPage));
    }

    @Tag(name = "Role Manage")
    @GetMapping("/get-role-manage-tree")
//    @Operation(summary = "根据筛选获取所有角色，返回树状结构")
    public Result<List<RoleManageTreeVo>> getRoleManageTree(RoleManageTreeQO queryCriteria) {
        List<RoleDto> allByQueryCriteriaToDtoToTree = this.getService().findAllByQueryCriteriaToDtoToTree((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return this.result(this.getMapper().toRoleManageTreeVo(allByQueryCriteriaToDtoToTree));
    }

    @Tag(name = "Role Manage")
    //    @GetMapping("/get-role-manage-detail-role")
    @GetMapping("/get-role-manage-role-detail-to-list")
    public Result<List<RoleManageRoleDetaiToListVo>> getRoleManageTenantDetaiToListAll(RoleManageRoleDetaiToListQO queryCriteria) {
        List<SysRole> all = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return result(getMapper().toRoleManageRoleDetaiToListVoToTree(all));
    }
//    @Operation(summary = "根据筛选获取所有角色，返回树状结构")
//    public Result<List<TreeNode<RoleManageRoleDetaiToListVo>>> getRoleManageRoleDetaiToListAll(RoleManageRoleDetaiToListQO queryCriteria) {
//
//        List<SysRole> all = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
//        return result( getMapper().toRoleManageDetailRoleTreeNodeVoDefault(all));
//    }

    @Tag(name = "Role Manage")
    @GetMapping("/get-role-manage-detail/{id}")
    @Operation(summary = "get-role-manage-detail")
    public Result<RoleManageDetailVo> getRoleManageDetail(@PathVariable String id) {
        return result(this.getMapper().toRoleManageDetailVo(getService().findById(id)));
    }

    @Tag(name = "Role Manage")
    @PostMapping("/insert-role-manage")
//    @Operation(summary = "新增一条数据")
    public Result insertRoleManage(@RequestBody @Validated InsertRoleManageIO insertRoleManageIO) {
        this.getService().insertAndUpdate(roleVoMapper.toEntity(insertRoleManageIO));
        return result();
    }

    @Tag(name = "Role Manage")
    @PutMapping("/update-role-manage")
//    @Operation(summary = "新增一条数据")
    public Result updateRoleManage(@RequestBody @Validated UpdateRoleManageIO updateRoleManageIO) {
        SysRole sysRole = this.getService().findById(updateRoleManageIO.getId());
        roleVoMapper.copy(updateRoleManageIO, sysRole);
        this.getService().insertAndUpdate(sysRole);
        return result();
    }

    @Tag(name = "Role Manage")
//    @Operation(summary = "删除一条数据")
    @DeleteMapping("/delete-role-manage/{id}")
    public Result deleteRoleManage(@PathVariable String id) {
        return deleteByIdToResult(id);
    }

    @Tag(name = "Role Manage")
//    @Operation(summary = "删除多条数据")
    @DeleteMapping("/delete-all-role-manage")
    public Result deleteAllRoleManage(List<String> id) {
        return deleteAllByIdToResult(id);
    }


    /*------------------------------------------ 以上是系统访问控制 --------------------------------------------*/

    @Tag(name = "Role Manage")
    @GetMapping("/get-all-tenant-by-role-id/{roleId}")
    @Operation(summary = "获取指定角色的所有菜单，返回id")
    public Result<List<String>> getAllTenantByRoleId(@PathVariable @RequestParam("roleId") String roleId) {
        return getService().getAllTenantByRoleId(roleId);
    }

    @Tag(name = "Role Manage")
    @PostMapping("/insert-role-menu")
//    @Operation(summary = "insertSave")
    public Result<String> insertRoleMenu(@RequestBody @Validated InsertRoleMenuIO insertRoleMenuIO) {
        return getService().insertRoleMenu(insertRoleMenuIO);
    }

    //    public SysRoleController(SysRoleService roleService, RoleVoMapper roleVoMapper) {
//        this.roleService = roleService;
//        this.roleVoMapper = roleVoMapper;
//    }
//
//    @GetMapping("/get-by-id")
//    @Operation(summary = "根据id获取实体数据")
//    public Result<RoleVo> getById(String id) {
//        return findByIdToResult(id);
//    }

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

//    @Operation(summary = "")
//    @DeleteMapping
//    public Result<RoleVo> deleteById(String id) {
//        return deleteByIdToResult(id);
//    }



}

