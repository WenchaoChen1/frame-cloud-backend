package com.gstdev.cloud.service.system.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gstdev.cloud.data.core.entity.BasePOJOEntity;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

/**
 * @author: zcy
 * @date: 2022/12/6
 * @description: 账户
 */
@Getter
@Setter
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
@Table(name = "sys_account", schema = "public")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE sys_account SET deleted=1 WHERE id =?")
public class SysAccount extends BasePOJOEntity {

    @Column(name = "tenant_id", length = 36, nullable = false)
    private String tenantId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "identity", length = 100)
    private String identity;

    //  super:0 看到所有数据最大权限,admin:1只能看到当前租户的所有权限，user：需要根据role来获取权限
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private SysAccountType type = SysAccountType.USER;

    @Schema(title = "数据状态")
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DataItemStatus status = DataItemStatus.ENABLE;

    @Column(name = "deleted", nullable = false)
    private Integer deleted = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private SysUser user;

    @JsonIgnore
    @ManyToMany(mappedBy = "accounts")
    private List<SysDepart> departs;
    @JsonIgnore
    @ManyToMany(mappedBy = "accounts")
    private List<SysRole> roles;
}
