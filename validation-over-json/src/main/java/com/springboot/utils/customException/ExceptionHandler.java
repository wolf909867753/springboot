package com.springboot.utils.customException;


import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by wanglu-jf on 17/7/20.
 */
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ResultMsg exceptionHandler(CustomerException e){
        ResultMsg msg = new ResultMsg(e);
        return msg;
    }
}
