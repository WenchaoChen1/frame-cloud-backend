package com.frame.template.service.system.pojo.vo.menu;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhucy
 */
@Getter
@Setter
public class DictSaveInput implements Serializable {
  String name;
  String code;
  String parentId;
  Integer status;
  Integer sort;
  String description;
}
