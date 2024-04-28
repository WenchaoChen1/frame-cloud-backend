package com.frame.template.service.demo.pojo.base.demo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class DemoInsertInput implements Serializable {

    String name;
    String code;

}
