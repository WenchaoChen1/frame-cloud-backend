// ====================================================
//
// This file is part of the GstDev Verity Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2020 gstdev.com
//
// ====================================================

package com.gstdev.template.service.system.message;


public enum OrganizationErrorMessage implements ErrorMessage {
  ORGANIZATION_NOT_EXISTS("ORGANIZATION_00001", "Not found"),
  ORGANIZATION_NAME_EXISTS("ORGANIZATION_00002", "name is already been taken");

  private String code;
  private String message;

  OrganizationErrorMessage(String code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
