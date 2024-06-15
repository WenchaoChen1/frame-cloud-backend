package com.gstdev.cloud.service.system.pojo.o.sysTenant;

import com.gstdev.cloud.service.system.enums.AccountTypeConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertAndUpdateTenantManageIO {
    private String id;

    @Schema(title = "parentId 不能为空", required = true)
    @NotEmpty
    private String parentId;
    private String tenantCode;
    private String tenantName;
    private String description;
    private Integer status;
    private Integer type;

    //-----------------自定义-----------

    private String companyName = "";
    private String website = "";
    private String addressLine1 = "";
    private String addressLine2 = "";
    private String city = "";
    private String state = "";
    private String country = "";
    private String zipCode = "";
    private String firstName = "";
    private String lastName = "";
    private String emailAddress = "";
    private String phoneNumber = "";
    private String logo = "";
}
