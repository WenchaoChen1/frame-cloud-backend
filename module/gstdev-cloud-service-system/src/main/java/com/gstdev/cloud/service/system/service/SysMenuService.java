package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.pojo.entity.Menu;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BaseTreeService;
import com.gstdev.cloud.service.system.pojo.base.menu.*;

import java.util.List;


public interface SysMenuService extends BaseTreeService<Menu, String, MenuDto> {
    Result<List<MenuDto>> getAllByRoleMenuToTree(String roleId);

    Result<MenuDto> getAllTenantMenuIds(String tenantId);

    List<MenuDto> getAccountPermissions(String accountId);

    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/


}
