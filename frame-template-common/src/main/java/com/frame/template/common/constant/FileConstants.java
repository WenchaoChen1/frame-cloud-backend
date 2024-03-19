package com.frame.template.common.constant;

public enum FileConstants {

  SERVICE_SYSTEM(ServiceConstants.SERVICE_NAME_SYSTEM,"service-system","SERVICE"),
  SERVICE_IDENTITY(ServiceConstants.SERVICE_NAME_IDENTITY,"system-identity","SERVICE"),
  SERVICE_TENANT(ServiceConstants.SERVICE_NAME_TENANT,"system-tenant","SERVICE"),
  FILE_TABLE_USER_LOGO("user-logo","user-logo",FileConstants.SERVICE_SYSTEM.getCode()),
  FILE_TABLE_TENANT_LOGO("tenant-logo","tenant-logo",FileConstants.SERVICE_TENANT.getCode()),
  DEFAULT("default",null,null);


  private final String name;
  private final String code;
  private final String service;

  FileConstants(String name, String code,String service) {
    this.name = name;
    this.code = code;
    this.service = service;
  }

  public String getService() {
    return service;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

}
