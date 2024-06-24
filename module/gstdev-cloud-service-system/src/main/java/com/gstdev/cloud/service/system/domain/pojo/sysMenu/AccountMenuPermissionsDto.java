package com.gstdev.cloud.service.system.domain.pojo.sysMenu;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.TreeNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountMenuPermissionsDto extends TreeNode<String, AccountMenuPermissionsDto> {

    private String id;
    private String code;
    private String icon;
    private String name;
    private String parentId;
    private String path;
    private Integer sort;
    private DataItemStatus status;
}
