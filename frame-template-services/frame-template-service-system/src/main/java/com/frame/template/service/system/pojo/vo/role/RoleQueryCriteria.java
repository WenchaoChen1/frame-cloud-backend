package com.frame.template.service.system.pojo.vo.role;

import com.gstdev.cloud.data.jpa.annotations.Query;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: zcy
 * @date: 2022/12/5
 * @description:
 */
@Getter
@Setter
public class RoleQueryCriteria {
  private String roleName;
  @Query
  private String code;
  @Query
  private String tenantId;
}
