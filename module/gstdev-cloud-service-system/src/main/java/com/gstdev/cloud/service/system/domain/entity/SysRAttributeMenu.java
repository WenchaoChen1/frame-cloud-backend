package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.data.core.entity.BasePOJOEntity;
import com.gstdev.cloud.service.system.domain.generator.SysRAttributeMenuEmbeddablePK;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


@Getter
@Setter
@Entity
@Table(name = "sys_r_attribute_menu", schema = "public")
//@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
public class SysRAttributeMenu extends BaseEntity {

    @EmbeddedId
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private SysRAttributeMenuEmbeddablePK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("menuId")  // 映射复合主键中的 menuId 字段
    @JoinColumn(name = "menu_id", insertable = false, updatable = false)
    private SysMenu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("attributeId")  // 映射复合主键中的 attributeId 字段
    @JoinColumn(name = "attribute_id", insertable = false, updatable = false)
    private SysAttribute attribute;

    public void setId(String menuId, String attributeId) {
        setId(new SysRAttributeMenuEmbeddablePK(menuId, attributeId));
    }

    //    @Id
//    @Column(name = "id")
//    private String id;
//
//    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinColumn(name = "menu_id", insertable = false, updatable = false)
//    private SysMenu menu;
//
//    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinColumn(name = "attribute_id", insertable = false, updatable = false)
//    private SysAttribute attribute;


}
