package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.data.core.service.BaseTreeService;
import com.gstdev.cloud.service.system.domain.base.menu.MenuDto;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.AccountMenuPermissionsDto;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.InsertMenuManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.UpdateMenuManageIO;

import java.util.List;


public interface SysMenuService extends BaseService<SysMenu, String> {

    List<AccountMenuPermissionsDto> getAccountMenuPermissions(String accountId);

    void updateMenuManage(UpdateMenuManageIO updateMenuManageIO);

    void insertMenuManage(InsertMenuManageIO insertMenuManageIO);


    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/


}
