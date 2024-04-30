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
//public enum ServiceErrorMessage implements ErrorMessage {
//
//    DB_OPERATION_FAILED("DB000001", "Database operation failed"),
//    RC_OPERATION_FAILED("RC000001", "Request failed"),
//    REQUEST_PARAMETER_ERROR("00001", "Invalid parameter"),
//    INVALID_ACCOUNT_EXIST("rest001", "This account does not exist"),
//    INVALID_ACCOUNT("rest008", "This email address is invalid"),
//    LINK_TIMED_OUT("rest002", "Sorry, this password reset link has been expired"),
//    LINK_TIMED_USER_OUT("rest002", "Sorry, this user activation link has been expired"),
//    RESET_PASSWORD_NOT_INITIATED("rest003", "Please send an email to reset your password first"),
//    PASSWORD_NEEDS_CHANGED("rest005", "The password needs to reset every 90 days"),
//    USER_LOCKED("rest006", "This account has been locked"),
//    RESET_MESSAGE_EXPIRED("rest007", "Your password has already been reset"),
//    USER_DISABLED("rest008", "This account has been deactivated"),
//    USER_INVITED("rest009", "This account has not been activated"),
//    USER_ALREADY_ACTIVE("activity01", "This user has been activeted"),
//    USER_ALREADY_TOKEN_ERROR("activity02", "The activation code is invalid"),
//    ACTIVATION_FAILED("activity03", "Activation failed"),
//    RESET_PASSWORD_EMAIL_SENT_SUCCESSFULLY("csv009", "Password reset email has been sent successfully"),
//    CSV_FILE_UPLOAD_FAILED("csv001", "Uploaded CSV file failed"),
//    PARTNER_NOT_AGREE("csv002", "Partner name does not consist"),
//    PARTNER_NOT_EXIST("csv003", "Partner name does not exist"),
//    PLEASE_FILL_OUT_THE_PARTNER("csv004", "Please fill out the Partner"),
//    PLEASE_FILL_OUT_THE_TEMPLATE("csv005", "Please fill out the Template"),
//    THE_ACTIVATION_CODE_INCORRECT("cuser001", "The activation code is invalid"),
//    THIS_USER_INSUFFICIENT_PERMISSIONS("permissions001", "Permission denied"),
//    USER_ADD_FAILED("user001", "Failed to add user"),
//
//    ;
//
//    private final String code;
//    private final String message;
//
//    ServiceErrorMessage(String code, String message) {
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
