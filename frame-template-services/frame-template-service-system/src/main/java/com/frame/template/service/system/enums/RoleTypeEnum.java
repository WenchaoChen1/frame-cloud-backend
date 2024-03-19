package com.frame.template.service.system.enums;

public enum RoleTypeEnum {
  SuperAdmin("Super Admin", 1),
  CompanyAdmin("Company Admin", 2),
  ProjectAdmin("Project Admin", 3),
  Member("Member", 4);

  private String name;
  private int value;

  private RoleTypeEnum(String name, int value){
    this.name = name;
    this.value = value;
  }


  public Integer getValue() {
    return value;
  }

  public String getName() {
    return name;
  }
}
