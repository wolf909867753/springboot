package com.springboot.utils.exceptions;

/**
 * Created by wanglu-jf on 17/7/20.
 */
public enum ExceptionEnum {
    SUCCESS("0","成功"),
    NO_PARAMETER("-1","参数丢失");

    private String code;
    private String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
