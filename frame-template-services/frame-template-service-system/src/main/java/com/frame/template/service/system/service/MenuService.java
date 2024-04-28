package com.frame.template.service.system.service;

import com.frame.template.service.system.pojo.base.menu.*;
import com.frame.template.service.system.pojo.entity.Menu;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BaseTreeService;

import java.util.List;

/**
 * @author zhucy
 */
public interface MenuService extends BaseTreeService<Menu, String, MenuDto, MenuInsertInput, MenuUpdateInput, MenuPageQueryCriteria, MenuFindAllByQueryCriteria> {
    Result<List<MenuDto>> getAllByRoleMenuToTree(String roleId);

    Result<MenuDto> getAllTenantMenuIds(String tenantId);

    List<MenuDto> getAccountPermissions(String accountId);

    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/


}
