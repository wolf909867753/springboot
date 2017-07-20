package com.springboot.utils.exceptions;

/**
 * Created by wanglu-jf on 17/7/20.
 */
public class GlobalErrorInfoException extends Exception {

    private ErrorInfoInterface errorInfo;

    public ErrorInfoInterface getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfoInterface errorInfo) {
        this.errorInfo = errorInfo;
    }

    public GlobalErrorInfoException(ErrorInfoInterface errorInfo) {
        this.errorInfo = errorInfo;
    }
}
