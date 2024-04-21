package com.frame.template.service.demo.pojo.base.demo;

import com.gstdev.cloud.data.core.annotations.Query;
import com.frame.template.common.base.BaseFindAllByQueryCriteria;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DemoFindAllByQueryCriteria extends BaseFindAllByQueryCriteria {
  @Query(blurry = "name")
  String name;
}
