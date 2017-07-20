package com.springboot.utils.exceptions;

/**
 * Created by wanglu-jf on 17/7/20.
 */
public enum CityErrorInfoEnum implements ErrorInfoInterface {

    PARAMS_NO_COMPLETE("000001","params no complete"),
    CITY_EXIT("000002","city exit");

    private String code;

    private String message;

    CityErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
