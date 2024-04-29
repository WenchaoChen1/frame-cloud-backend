package com.frame.template.service.system.pojo.entity;

import com.frame.template.service.system.pojo.generator.SysInterfaceUuidGenerator;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.gstdev.cloud.data.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: 系统应用程序接口实体 </p>
 *
 */
@Schema(name = "系统应用接口")
@Entity
@Getter
@Setter
@Table(name = "sys_interface", indexes = {@Index(name = "sys_interface_id_idx", columnList = "interface_id")})
public class SysInterface extends BaseEntity {

    @Schema(name = "接口ID")
    @Id
    @SysInterfaceUuidGenerator
    @Column(name = "interface_id", length = 64)
    private String interfaceId;

    @Schema(name = "接口代码")
    @Column(name = "interface_code", length = 128)
    private String interfaceCode;

    @Schema(name = "请求方法")
    @Column(name = "request_method", length = 20)
    private String requestMethod;

    @Schema(name = "服务ID")
    @Column(name = "service_id", length = 128)
    private String serviceId;

    @Schema(name = "接口所在类")
    @Column(name = "class_name", length = 512)
    private String className;

    @Schema(name = "接口对应方法")
    @Column(name = "method_name", length = 128)
    private String methodName;

    @Schema(name = "请求URL")
    @Column(name = "url", length = 2048)
    private String url;

    /**
     * 角色描述,UI界面显示使用
     */
    @Schema(name = "备注")
    @Column(name = "description", length = 512)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysInterface that = (SysInterface) o;
        return Objects.equal(interfaceId, that.interfaceId) && Objects.equal(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(interfaceId, serviceId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("interfaceId", interfaceId)
                .add("interfaceCode", interfaceCode)
                .add("requestMethod", requestMethod)
                .add("serviceId", serviceId)
                .add("className", className)
                .add("methodName", methodName)
                .add("url", url)
                .toString();
    }
}
