package com.gstdev.cloud.service.system.domain.pojo.sysAccount;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateAccountAssignedRoleIO {

    @NotBlank
    private String accountId;
    private List<String> roleIds;
}
