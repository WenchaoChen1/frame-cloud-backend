package com.gstdev.template.service.system.pojo.vo.depart;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhucy
 */
@Getter
@Setter
public class DepartModifyInput {
  private String id;
  private String tenantId;
  private String parentId;
  private String name;
  private String code;
  private String shortName;
  private Integer status;
  private Integer sort;
  private String description;
  private Integer deleted;
}
