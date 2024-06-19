package com.gstdev.cloud.service.system.domain.pojo.sysTenant;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertTenantManageIO {
    @Schema(title = "parentId 不能为空", required = true)
    @NotEmpty
    private String parentId="0";
    private String tenantCode;
    private String tenantName;
    private String description;
    private DataItemStatus status = DataItemStatus.ENABLE;
    private Integer type;

    //-----------------自定义-----------

//    private String companyName = "";
//    private String website = "";
//    private String addressLine1 = "";
//    private String addressLine2 = "";
//    private String city = "";
//    private String state = "";
//    private String country = "";
//    private String zipCode = "";
//    private String firstName = "";
//    private String lastName = "";
//    private String emailAddress = "";
//    private String phoneNumber = "";
//    private String logo = "";
}
