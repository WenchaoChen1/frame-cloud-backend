package com.gstdev.template.common.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class BaseDto implements Serializable {
  String id;
}
