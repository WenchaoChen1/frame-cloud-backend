package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.service.system.domain.generator.SysRAccountRoleEmbeddablePK;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "sys_r_account_role", schema = "public")
@IdClass(SysRAccountRoleEmbeddablePK.class)
public class SysRAccountRole extends BaseEntity {

    @Id
    private String accountId;
    @Id
    private String roleId;
}
