package com.gstdev.template.service.system.pojo.vo.TenantDict;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhucy
 */
@Getter
@Setter
public class TenantDictSaveInput implements Serializable {
  String name;
  String code;
  String parentId;
  String tenantId;
  Integer status;
  Integer sort;
  String description;
}
