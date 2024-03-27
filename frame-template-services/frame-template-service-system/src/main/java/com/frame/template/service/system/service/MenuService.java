package com.frame.template.service.system.service;

import com.frame.template.service.system.pojo.base.menu.*;
import com.gstdev.cloud.commons.domain.Result;
import com.frame.template.common.base.baseTree.BaseTreeService;
import com.frame.template.service.system.pojo.base.menu.*;

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
