package com.frame.template.service.system.pojo.vo.depart;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhucy
 */
@Getter
@Setter
public class DepartSaveInput implements Serializable {
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
