//// ====================================================
////
//// This file is part of the GstDev Verity Platform.
////
//// Create by GstDev <support@gstdev.com>
//// Copyright (c) 2020-2020 gstdev.com
////
//// ====================================================
//
//package com.gstdev.cloud.service.system.message;
//
//public enum UserErrorMessage implements ErrorMessage {
//
//    USER_NOT_EXISTS("USER_000001", "This user name does not exist"),
//    USER_EMAIL_EXISTS("USER_000002", "This email has been taken"),
//    USER_NAME_EXISTS("USER_000003", "This User name has been taken"),
//    USER_PHONE_NUMBER_EXISTS("USER_000004", "This phone number has been taken"),
//    USER_NAME_IS_EMPTY("USER_000005", "The user name is required"),
//    USER_PASSWORD_IS_EMPTY("USER_000001", "The password is required"),
//    USER_ID_IS_EMPTY("USER_000001", "The user ID is required"),
//    USER_PASSWORD_ERROR("USER_000002", "The user password is incorrect"),
//    THE_PASSWORD_INVALID("USER_000003", "The password is invalid"),
//    EMAIL_IS_NOT_EXIST("email001", "The email is invalid"),
//    PERMISSION_DENIED("email002", "Permission denied"),
//
//    ;
//
//    private String code;
//    private String message;
//
//    UserErrorMessage(String code, String message) {
//        this.code = code;
//        this.message = message;
//    }
//
//    @Override
//    public String getCode() {
//        return code;
//    }
//
//    @Override
//    public String getMessage() {
//        return message;
//    }
//}
