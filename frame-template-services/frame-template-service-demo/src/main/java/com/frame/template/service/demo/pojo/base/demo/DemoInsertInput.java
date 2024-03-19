package com.frame.template.service.demo.pojo.base.demo;

import com.frame.template.common.base.BaseInsertInput;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DemoInsertInput extends BaseInsertInput {

  String name;
  String code;

}
