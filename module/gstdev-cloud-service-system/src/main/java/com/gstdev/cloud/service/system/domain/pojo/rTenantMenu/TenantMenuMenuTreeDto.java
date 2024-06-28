package com.gstdev.cloud.service.system.domain.pojo.rTenantMenu;

import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.util.TreeNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantMenuMenuTreeDto extends TreeNode<String, TenantMenuMenuTreeDto> {
    private String tenantMenuId;
    private String id;
    private String parentId;
    private Integer sort;

    private String menuName;
    private String name;
    private String path;

    void setMenu(SysMenu menu) {
        this.id = menu.getId();
        this.menuName = menu.getMenuName();
        this.name = menu.getName();
        this.path = menu.getPath();
    }
}
