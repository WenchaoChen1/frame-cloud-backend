package com.frame.template.service.system.pojo.vo.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountQueryCriteria {
    String mobile;
    String email;
    String tenantId;
}
