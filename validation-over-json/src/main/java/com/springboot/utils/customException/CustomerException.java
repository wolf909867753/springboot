package com.springboot.utils.customException;

/**
 * Created by wanglu-jf on 17/7/20.
 */
public class CustomerException extends Exception {
    private String code;
    private String message;

    public CustomerException(String code,String message) {
        this.code = code;
        this.message = message;
    }

    public CustomerException(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
