package com.gstdev.cloud.service.system.pojo.o.sysMenu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMenuManageIO {
    private String id;
    private String menuName;
    private String code;
    private String description;
    private Integer hidden;
    private String icon;
    private String name;
    private String parentId;
    private String path;
    private String permission;
    private Integer sort;
    private Integer status;
    private Integer tenantEnable;
    private Integer type;
    private String url;
    private String location;
}
