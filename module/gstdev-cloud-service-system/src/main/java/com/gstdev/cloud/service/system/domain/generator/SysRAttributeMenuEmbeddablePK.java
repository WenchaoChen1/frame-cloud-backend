package com.gstdev.cloud.service.system.domain.generator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Embeddable
public class SysRAttributeMenuEmbeddablePK {
    private String menuId;
    private String attributeId;

    public SysRAttributeMenuEmbeddablePK(String menuId, String attributeId) {
        this.menuId = menuId;
        this.attributeId = attributeId;
    }

    public SysRAttributeMenuEmbeddablePK() {

    }
}
