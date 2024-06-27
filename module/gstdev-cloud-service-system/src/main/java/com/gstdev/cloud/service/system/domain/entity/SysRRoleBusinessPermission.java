package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.service.system.domain.generator.SysRRoleBusinessPermissionEmbeddablePK;
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
@Table(name = "sys_r_role_business_permission", schema = "public")
@IdClass(SysRRoleBusinessPermissionEmbeddablePK.class)
public class SysRRoleBusinessPermission extends BaseEntity {

    @Id
    private String roleId;
    @Id
    private String businessPermissionId;
}
