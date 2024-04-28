package com.frame.template.service.demo.pojo.base.demo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DemoUpdateInput extends BaseUpdateInput {

    String id;
    String name;
    String code;

}
