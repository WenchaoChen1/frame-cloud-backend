package com.frame.template.service.system.controller;

import com.frame.template.service.system.mapper.vo.RoleVoMapper;
import com.frame.template.service.system.pojo.base.role.*;
import com.gstdev.cloud.commons.domain.Result;
import com.frame.template.common.base.baseTree.BaseTreeController;
import com.frame.template.service.system.pojo.base.role.*;
import com.frame.template.service.system.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/v1/role")
public class RoleController extends BaseTreeController<RoleService, RoleVoMapper, RoleVo, RoleDto, RoleInsertInput, RoleUpdateInput, RolePageQueryCriteria, RoleFindAllByQueryCriteria> {

  @Resource
  private RoleService roleService;

  @Resource
  private RoleVoMapper roleVoMapper;

  public RoleController(RoleService roleService, RoleVoMapper roleVoMapper) {
    super(roleService, roleVoMapper);
    this.roleService = roleService;
    this.roleVoMapper = roleVoMapper;
  }

  @GetMapping("/get-all-role-to-tree")
  @ApiOperation("根据筛选获取所有角色，返回树状结构")
  public Result<List<RoleVo>> findAllByQueryCriteriaToTree(RoleFindAllByQueryCriteria roleFindAllByQueryCriteria) {
    return findAllByQueryCriteriaToResultToTree(roleFindAllByQueryCriteria);
  }

  @GetMapping("/get-by-id")
  @ApiOperation("根据id获取实体数据")
  public Result<RoleVo> getById(String id) {
    return findByIdToResult(id);
  }

  @PostMapping
  @ApiOperation("新增一条数据")
  public Result<RoleVo> insert(@RequestBody RoleInsertInput roleInsertInput) {
    return insertToResult(roleInsertInput);
  }

  @PutMapping
  @ApiOperation("修改一条数据")
  public Result<RoleVo> update(@RequestBody RoleUpdateInput roleUpdateInput) {
    return updateToResult(roleUpdateInput);
  }

  @ApiOperation("")
  @DeleteMapping
  public Result<RoleVo> deleteById(String id) {
    return deleteByIdToResult(id);
  }

  @GetMapping("/get-all-by-role-id")
  @ApiOperation("获取指定角色的所有菜单，返回id")
  public Result<List<String>> getAllByRoleId(@RequestParam("roleId") String roleId) {
     return getService().getAllByRoleId(roleId);
  }

  @PostMapping("/insertRoleMenu")
  @ApiOperation("insertSave")
  public Result<String> insertRoleMenu(@RequestBody RoleInsertInput roleInsertInput) {
    return getService().insertRoleMenu(roleInsertInput);
  }

  /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/


}

