package com.frame.template.service.system.pojo.vo.TenantDict;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhucy
 */
@Getter
@Setter
public class TenantDictModifyInput {
  String id;
  String name;
  String parentId;
  String tenantId;
  String code;
  Integer status;
  Integer sort;
  String description;
}
