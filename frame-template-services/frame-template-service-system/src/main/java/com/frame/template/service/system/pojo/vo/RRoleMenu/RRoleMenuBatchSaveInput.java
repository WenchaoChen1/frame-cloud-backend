package com.frame.template.service.system.pojo.vo.RRoleMenu;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: zcy
 * @date: 2022/12/5
 * @description:
 */
@Getter
@Setter
public class RRoleMenuBatchSaveInput {
    private String roleId;
    /**
     * 全选
     */
    private List<String> checkedMenuId;
    /**
     * 半选
     */
    private List<String> halfCheckedMenuId;
}
