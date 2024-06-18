package com.gstdev.cloud.service.system.domain.pojo.sysMenu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMenuManageIO {
    private String id;
    private String menuName;
    private String parentId;
    private String code;
    private Integer sort;
    private Integer type;
    private String location;
    private String description;
    private Integer status;


    private String name;
    private String path;
    private String icon;

}