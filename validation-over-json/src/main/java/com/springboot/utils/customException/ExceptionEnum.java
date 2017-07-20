package com.springboot.utils.customException;

/**
 * Created by wanglu-jf on 17/7/20.
 */
public enum ExceptionEnum {
    SUCCESS("0","成功"),FAIL("-1","失败"),NO_PARAMETER("-2","请求参数缺失");


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
