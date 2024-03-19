package com.gstdev.template.service.demo.pojo.dto.demo;

import com.gstdev.cloud.data.jpa.annotations.Query;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DemoXXXXQueryCriteria {
  @Query(blurry = "name")
  String name;
  String sdsadad;
  String asdsa;
  String asdsad;
}
