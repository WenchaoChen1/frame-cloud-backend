// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.pojo.vo.tenant;

import com.gstdev.cloud.data.core.pojo.BaseTreeInsertInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.NotEmpty;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantLoginInferiorInsertInput extends BaseTreeInsertInput {
    //  @Schema(title = "parentId 不能为空", required = true)
//  @NotEmpty
    private String parentId;
    private String tenantCode;
    @Schema(title = "companyName 不能为空", required = true)
    @NotEmpty
    private String tenantName;
    private String description;
    private Integer status;
    private Integer type;

    //-----------------自定义-----------

    private String website;
    @Schema(title = "addressLine1 不能为空", required = true)
    @NotEmpty
    private String addressLine1;
    @Schema(title = "addressLine2 不能为空")
    private String addressLine2;
    @Schema(title = "city 不能为空", required = true)
    @NotEmpty
    private String city;
    @Schema(title = "state 不能为空", required = true)
    @NotEmpty
    private String state;
    @Schema(title = "country 不能为空", required = true)
    @NotEmpty
    private String country;
    @Schema(title = "zipCode 不能为空", required = true)
    @NotEmpty
    private String zipCode;
    @Schema(title = "firstName 不能为空", required = true)
    @NotEmpty
    private String firstName;
    @Schema(title = "lastName 不能为空", required = true)
    @NotEmpty
    private String lastName;
    @Schema(title = "emailAddress 不能为空", required = true)
    @NotEmpty
    private String emailAddress;
    @Schema(title = "phoneNumber 不能为空", required = true)
    @NotEmpty
    private String phoneNumber;
    private String logo;
}

