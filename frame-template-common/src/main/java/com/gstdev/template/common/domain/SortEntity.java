package com.gstdev.template.common.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SortEntity {

  private Integer sort;

  private String exportName;

  private String attributeName;
}
