// ====================================================
//
// This file is part of the GstDev Verity Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2020 gstdev.com
//
// ====================================================

package com.frame.template.service.system.message;

public enum BaseErrorMessage implements ErrorMessage {

  SAVE_SUCCESS("base001", "Saved successfully"),
  FAIL_SAVE("base002", "Save failed"),
  MODIFY_SUCCESS("base003", "Saved successfully"),
  FAIL_MODIFY("base004", "Save failed"),
  INFORMATION_NOT_EXIST("base005", "This information does not exists"),
  SUCCESSF_DELETE("base006", "Deleted successfully"),
  SUCCESSF_ACTIVITY("base007", "Activation successful"),
  INFORMATION_ALREADY_EXIST("base008", "This name already exists"),
  ;

  private String code;
  private String message;

  BaseErrorMessage(String code, String message) {
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
