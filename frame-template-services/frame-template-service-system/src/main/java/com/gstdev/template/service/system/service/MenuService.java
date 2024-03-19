package com.gstdev.template.service.system.service;

import com.gstdev.cloud.commons.web.Result;
import com.gstdev.template.common.base.baseTree.BaseTreeService;
import com.gstdev.template.service.system.pojo.base.menu.*;

import java.util.List;

/**
 * @author zhucy
 */
public interface MenuService extends BaseTreeService<MenuDto, MenuInsertInput, MenuUpdateInput, MenuPageQueryCriteria, MenuFindAllByQueryCriteria> {
    Result<List<MenuDto>> getAllByRoleMenuToTree(String roleId);

  Result<MenuDto> getAllTenantMenuIds(String tenantId);

  List<MenuDto> getAccountPermissions(String accountId);

  /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/




}
