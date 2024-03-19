package com.frame.template.common.domain;

import lombok.Data;

import java.lang.reflect.Parameter;

@Data
public class ParameterGstDev {

  private String paramName;

  private Parameter parameter;

  private Object paramValue;

}
