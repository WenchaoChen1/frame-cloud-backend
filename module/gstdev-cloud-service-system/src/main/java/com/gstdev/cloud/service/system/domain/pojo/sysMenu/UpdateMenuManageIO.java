package com.gstdev.cloud.service.system.domain.pojo.sysMenu;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysMenuType;
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
    private SysMenuType type;
    private String location;
    private String description;
    private DataItemStatus status;

    private String name;
    private String path;
    private String icon;
}
