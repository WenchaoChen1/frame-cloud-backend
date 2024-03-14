package com.gstdev.template.service.system.pojo.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.template.common.constant.AccountTypeConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class AccountListDto {

  private String id;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createdAt;
  private String createdBy;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date updatedAt;
  private String updatedBy;
  private String identity;
  private String name;
  private String type;
  private AccountTypeConstants accountTypeConstants;
  public AccountTypeConstants getAccountTypeConstants() {
    return AccountTypeConstants.getAccountTypeConstants(this.type);
  }
}
