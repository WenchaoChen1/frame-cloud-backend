package com.gstdev.cloud.service.system.pojo.o.sysMenu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertMenuManageIO {
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
