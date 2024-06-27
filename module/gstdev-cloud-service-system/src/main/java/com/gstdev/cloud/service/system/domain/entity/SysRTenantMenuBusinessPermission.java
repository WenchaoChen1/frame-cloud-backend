package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.service.system.domain.generator.SysRAttributeMenuEmbeddablePK;
import com.gstdev.cloud.service.system.domain.generator.SysRTenantMenuBusinessPermissionEmbeddablePK;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name = "sys_r_tenant_menu_business_permission", schema = "public")
@IdClass(SysRTenantMenuBusinessPermissionEmbeddablePK.class)
public class SysRTenantMenuBusinessPermission extends BaseEntity {

    @Id
    private String tenantMenuId;
    @Id
    private String businessPermissionId;
}
