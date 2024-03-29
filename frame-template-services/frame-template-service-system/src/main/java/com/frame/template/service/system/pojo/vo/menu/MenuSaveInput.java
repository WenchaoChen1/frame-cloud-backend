package com.frame.template.service.system.pojo.vo.menu;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhucy
 */
@Getter
@Setter
public class MenuSaveInput implements Serializable {
  String name;
  String parentId;
  String url;
  String icon;
  String path;
  String permission;
  Integer type;
  Integer hidden;
  Integer status;
  Integer tenantEnable;
  Integer sort;
  String description;
}
