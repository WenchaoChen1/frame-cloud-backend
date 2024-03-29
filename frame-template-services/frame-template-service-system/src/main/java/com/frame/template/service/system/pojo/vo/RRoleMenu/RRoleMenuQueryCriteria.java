package com.frame.template.service.system.pojo.vo.RRoleMenu;

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
public class RRoleMenuQueryCriteria {
  @Query
  String tenantId;
  @Query(propName = "id", joinName = "role", join = Query.Join.LEFT)
  String roleId;
}
