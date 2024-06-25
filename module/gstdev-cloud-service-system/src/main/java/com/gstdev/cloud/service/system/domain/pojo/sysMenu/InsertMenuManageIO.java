package com.gstdev.cloud.service.system.domain.pojo.sysMenu;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysMenuLocation;
import com.gstdev.cloud.service.system.domain.enums.SysMenuType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertMenuManageIO {
    private String menuName;
    private String parentId;
    private String code;
    private Integer sort;
    private SysMenuType type;
    private SysMenuLocation location;
    private String description;
    private DataItemStatus status;


    private String name;
    private String path;
    private String icon;

}
