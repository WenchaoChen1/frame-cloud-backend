package com.gstdev.template.service.demo.pojo.base.demo;

import com.gstdev.cloud.data.jpa.annotations.Query;
import com.gstdev.template.common.base.BaseFindAllByQueryCriteria;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DemoFindAllByQueryCriteria extends BaseFindAllByQueryCriteria {
  @Query(blurry = "name")
  String name;
}
