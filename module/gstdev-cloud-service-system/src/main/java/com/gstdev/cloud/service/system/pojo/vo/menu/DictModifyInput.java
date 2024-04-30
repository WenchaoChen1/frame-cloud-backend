package com.gstdev.cloud.service.system.pojo.vo.menu;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DictModifyInput {
    String id;
    String name;
    String parentId;
    String code;
    Integer status;
    Integer sort;
    String description;
}
