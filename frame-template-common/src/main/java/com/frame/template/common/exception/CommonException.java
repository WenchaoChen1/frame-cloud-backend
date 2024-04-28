package com.frame.template.common.exception;


/**
 * 通用异常类
 *
 * @Author: zcy
 * @Data: 2022/12/07
 */
public class CommonException extends RuntimeException {

    /**
     * 异常码，注意，异常码不同于API返回中的错误码
     */
    private Integer exceptionCode;
    /**
     * 异常信息，注意，异常信息不同于API返回中的错误信息
     */
    private String exceptionMessage;

    public CommonException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }

    public CommonException(Integer exceptionCode, String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }

    public Integer getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(Integer exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
