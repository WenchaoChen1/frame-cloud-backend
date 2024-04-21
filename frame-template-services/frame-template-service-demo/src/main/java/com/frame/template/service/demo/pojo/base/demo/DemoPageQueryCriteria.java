package com.frame.template.service.demo.pojo.base.demo;

import com.gstdev.cloud.data.core.annotations.Query;
import com.frame.template.common.base.BasePageQueryCriteria;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DemoPageQueryCriteria extends BasePageQueryCriteria {
  @Query(blurry = "name")
  String name;
}
