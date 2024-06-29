package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.service.system.domain.generator.SysRRoleTenantMenuEmbeddablePK;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "sys_r_role_tenant_menu", schema = "public")
@IdClass(SysRRoleTenantMenuEmbeddablePK.class)
public class SysRRoleTenantMenu extends BaseEntity {

    @Id
    private String roleId;
    @Id
    private String tenantMenuId;
}
