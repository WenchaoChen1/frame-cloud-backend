//package com.frame.template.service.system.pojo.entity;
//
//import com.frame.template.service.system.pojo.generator.SysAttributeUuidGenerator;
//import com.frame.template.service.system.pojo.listener.SysAttributeEntityListener;
//import com.gstdev.cloud.data.core.entity.BaseEntity;
//import io.swagger.v3.oas.annotations.media.Schema;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * <p>Description: 系统安全属性实体 </p>
// *
// */
//@Schema(title = "系统安全属性数据")
//@Getter
//@Setter
//@Entity
//@Table(name = "sys_attribute", indexes = {@Index(name = "sys_attribute_id_idx", columnList = "attribute_id")})
//@EntityListeners(value = {SysAttributeEntityListener.class})
//public class SysAttribute extends BaseEntity {
//
//    @Schema(title = "元数据ID")
//    @Id
//    @SysAttributeUuidGenerator
//    @Column(name = "attribute_id", length = 64)
//    private String attributeId;
//
//    @Schema(title = "默认权限代码")
//    @Column(name = "attribute_code", length = 128)
//    private String attributeCode;
//
//    @Schema(name = "请求方法")
//    @Column(name = "request_method", length = 20)
//    private String requestMethod;
//
//    @Schema(name = "服务ID")
//    @Column(name = "service_id", length = 128)
//    private String serviceId;
//
//    @Schema(name = "接口所在类")
//    @Column(name = "class_name", length = 512)
//    private String className;
//
//    @Schema(name = "接口对应方法")
//    @Column(name = "method_name", length = 128)
//    private String methodName;
//
//    @Schema(name = "请求URL")
//    @Column(name = "url", length = 2048)
//    private String url;
//
//    @Schema(title = "表达式", description = "Security表达式字符串，通过该值设置动态权限")
//    @Column(name = "web_expression", length = 128)
//    private String webExpression;
//
//    @Schema(name = "属性对应权限", title = "根据属性关联权限数据")
//    @ManyToMany(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.SUBSELECT)
//    @JoinTable(name = "sys_attribute_permission",
//        joinColumns = {@JoinColumn(name = "attribute_id")},
//        inverseJoinColumns = {@JoinColumn(name = "permission_id")},
//        uniqueConstraints = {@UniqueConstraint(columnNames = {"attribute_id", "permission_id"})},
//        indexes = {@Index(name = "sys_attribute_permission_aid_idx", columnList = "attribute_id"), @Index(name = "sys_attribute_permission_pid_idx", columnList = "permission_id")})
//    private Set<SysPermission> permissions = new HashSet<>();
