package com.gstdev.cloud.service.system.pojo.vo.role;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: zcy
 * @date: 2022/12/5
 * @description:
 */
@Getter
@Setter
public class RoleModifyInput {
    private String id;

    private String parentId;

    private String roleName;

    private String code;

    private Integer status;

    private Integer sort;

    private String tenantId;

    private String description;
}
