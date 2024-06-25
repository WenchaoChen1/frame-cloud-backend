//package com.gstdev.cloud.service.system.domain.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.google.common.base.MoreObjects;
//import com.google.common.base.Objects;
//import com.gstdev.cloud.data.core.entity.BasePOJOEntity;
//import com.gstdev.cloud.data.core.enums.DataItemStatus;
//import com.gstdev.cloud.service.system.domain.generator.SysPermissionUuidGenerator;
//import io.swagger.v3.oas.annotations.media.Schema;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.GenericGenerator;
//
//import java.util.List;
//
///**
// * <p>Description: 系统权限实体 </p>
// */
//@Getter
//@Setter
//@Entity
//@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
//@Table(name = "sys_business_permission", schema = "public")
//public class SysBusinessPermission extends BasePOJOEntity {
//
//    @Schema(title = "权限代码")
//    @Column(name = "permission_code", length = 128)
//    private String permissionCode;
//
//    @Schema(title = "权限名称")
//    @Column(name = "permission_name", length = 1024)
//    private String permissionName;
//
//
//    @Schema(title = "权限类型")
//    @Column(name = "permission_type", length = 1024)
//    private String permissionType;
//
//
//    @Schema(title = "数据状态")
//    @Column(name = "status", nullable = false)
//    @Enumerated(EnumType.ORDINAL)
//    private DataItemStatus status = DataItemStatus.ENABLE;
//
//
//    @JsonIgnore
//    @ManyToMany(mappedBy = "permissions")
//    private List<SysAttribute> sysAttributes;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        SysBusinessPermission that = (SysBusinessPermission) o;
//        return Objects.equal(permissionId, that.permissionId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(permissionId);
//    }
//
//    @Override
//    public String toString() {
//        return MoreObjects.toStringHelper(this)
//                .add("permissionId", permissionId)
//                .add("permissionCode", permissionCode)
//                .add("permissionName", permissionName)
//                .toString();
//    }
//}
