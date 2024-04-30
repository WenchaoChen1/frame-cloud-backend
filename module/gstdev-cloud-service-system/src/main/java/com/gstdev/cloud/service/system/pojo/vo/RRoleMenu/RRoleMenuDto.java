package com.gstdev.cloud.service.system.pojo.vo.RRoleMenu;

import com.gstdev.cloud.service.system.pojo.vo.menu.MenuDto;
import com.gstdev.cloud.service.system.pojo.vo.role.RoleDto;
import com.gstdev.cloud.service.system.pojo.vo.menu.MenuDto;
import com.gstdev.cloud.service.system.pojo.vo.role.RoleDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: zcy
 * @date: 2022/12/5
 * @description:
 */
@Getter
@Setter
public class RRoleMenuDto {
    String id;
    RoleDto role;
    MenuDto menu;
    Integer checked;
}
