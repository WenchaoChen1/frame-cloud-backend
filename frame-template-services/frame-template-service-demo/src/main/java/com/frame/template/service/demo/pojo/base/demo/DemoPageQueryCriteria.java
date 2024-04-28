package com.frame.template.service.demo.pojo.base.demo;

import com.gstdev.cloud.data.core.annotations.Query;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class DemoPageQueryCriteria implements Serializable {
    @Query(blurry = "name")
    String name;
}
