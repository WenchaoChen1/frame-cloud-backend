// ====================================================
//
// This file is part of the GstDev Verity Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2020 gstdev.com
//
// ====================================================

package com.frame.template.service.system.enums;

public enum UserStatus {
  /**
   * Enabled Status
   */
  PENDING("Pending",2),
  ACTIVE("Active", 1);

  private String name;
  private Integer value;

  UserStatus(String name, Integer value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }
}
