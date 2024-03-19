package com.frame.template.common.feign;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: zcy
 * @date: 2022/12/7
 * @description:
 */
@Getter
@Setter
@ToString
public class ApiResult<T> {
  private Boolean success;
  private String message;
  private String code;
  private T data;

  public ApiResult() {
  }
}
