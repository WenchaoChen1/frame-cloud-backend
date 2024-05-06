package com.gstdev.cloud.service.system.pojo.entity;

import com.gstdev.cloud.service.system.constants.SystemConstants;
import com.gstdev.cloud.service.system.pojo.generator.SysPermissionUuidGenerator;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.service.system.constants.SystemConstants;
import com.gstdev.cloud.service.system.pojo.generator.SysPermissionUuidGenerator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

/**
 * <p>Description: 系统权限实体 </p>
 */
@Schema(name = "系统权限")
@Getter
@Setter
@Entity
@Table(name = "sys_permission", indexes = {@Index(name = "sys_permission_id_idx", columnList = "permission_id")})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = SystemConstants.REGION_SYS_PERMISSION)
public class SysPermission extends BaseEntity {

    @Schema(name = "权限ID")
    @Id
    @SysPermissionUuidGenerator
    @Column(name = "permission_id", length = 64)
    private String permissionId;

    @Schema(name = "权限代码")
    @Column(name = "permission_code", length = 128)
    private String permissionCode;

    @Schema(name = "权限名称")
    @Column(name = "permission_name", length = 1024)
    private String permissionName;


    @Schema(name = "权限类型")
    @Column(name = "permission_type", length = 1024)
    private String permissionType;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysPermission that = (SysPermission) o;
        return Objects.equal(permissionId, that.permissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(permissionId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("permissionId", permissionId)
            .add("permissionCode", permissionCode)
            .add("permissionName", permissionName)
            .toString();
    }
}