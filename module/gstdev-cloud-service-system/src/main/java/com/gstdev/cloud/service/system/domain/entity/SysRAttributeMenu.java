package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.data.core.entity.BasePOJOEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


@Getter
@Setter
@Entity
@Table(name = "sys_r_attribute_menu", schema = "public")
//@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
public class SysRAttributeMenu extends BasePOJOEntity {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id", insertable = false, updatable = false)
    private SysMenu menu;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "attribute_id", insertable = false, updatable = false)
    private SysAttribute attribute;


}
