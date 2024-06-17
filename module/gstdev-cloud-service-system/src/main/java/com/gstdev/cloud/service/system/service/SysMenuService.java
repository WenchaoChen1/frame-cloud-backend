package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BaseTreeService;
import com.gstdev.cloud.service.system.domain.base.menu.*;

import java.util.List;


public interface SysMenuService extends BaseTreeService<SysMenu, String, MenuDto> {
    Result<List<MenuDto>> getAllByRoleMenuToTree(String roleId);

    Result<MenuDto> getAllTenantMenuIds(String tenantId);

    List<MenuDto> getAccountPermissions(String accountId);

    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/


}
