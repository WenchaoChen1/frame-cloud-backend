package com.gstdev.template.common.constant;

public enum AccountTypeConstants {

  SUPER("super","0"),
  ADMIN("admin","1"),
  USER("user","2");


  private final String name;
  private final String code;

  AccountTypeConstants(String name, String code) {
    this.name = name;
    this.code = code;
  }


  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public static AccountTypeConstants getAccountTypeConstants(String code) {
    for (AccountTypeConstants constant : AccountTypeConstants.values()) {
      if (constant.getCode().equals(code)) {
        return constant;
      }
    }
    return AccountTypeConstants.USER;
  }


}
