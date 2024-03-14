package com.gstdev.template.service.system.pojo.vo.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhucy
 */
@Getter
@Setter
public class DictModifyInput {
  String id;
  String name;
  String parentId;
  String code;
  Integer status;
  Integer sort;
  String description;
}
