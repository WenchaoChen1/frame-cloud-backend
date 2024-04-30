package com.gstdev.cloud.service.system.pojo.vo.RRoleMenu;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: zcy
 * @date: 2022/12/5
 * @description:
 */
@Getter
@Setter
public class RRoleMenuSaveInput {
    private String roleId;

    private String menuId;

    private Integer checked;
}
