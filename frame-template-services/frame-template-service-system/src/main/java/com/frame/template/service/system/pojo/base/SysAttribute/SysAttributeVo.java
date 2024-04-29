package com.frame.template.service.system.pojo.base.SysAttribute;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysAttributeVo {

    private String attributeId;

    @Schema(title = "默认权限代码")
    private String attributeCode;

    @Schema(name = "请求方法")
    private String requestMethod;

    @Schema(name = "服务ID")
    private String serviceId;

    @Schema(name = "接口所在类")
    private String className;

    @Schema(name = "接口对应方法")
    private String methodName;

    @Schema(name = "请求URL")
    private String url;

    @Schema(title = "表达式", description = "Security表达式字符串，通过该值设置动态权限")
    private String webExpression;

    /**
     * 角色描述,UI界面显示使用
     */
    @Schema(name = "备注")
    private String description;
}
