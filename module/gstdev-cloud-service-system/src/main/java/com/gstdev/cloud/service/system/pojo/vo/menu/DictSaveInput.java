package com.gstdev.cloud.service.system.pojo.vo.menu;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class DictSaveInput implements Serializable {
    String name;
    String code;
    String parentId;
    Integer status;
    Integer sort;
    String description;
}
