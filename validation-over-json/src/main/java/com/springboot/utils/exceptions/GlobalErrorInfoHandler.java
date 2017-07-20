package com.springboot.utils.exceptions;

import com.springboot.domain.vo.ResultMsg;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wanglu-jf on 17/7/20.
 */
@RestControllerAdvice
public class GlobalErrorInfoHandler {

    @ExceptionHandler(value = GlobalErrorInfoException.class)
    public ResultMsg errorHandlerOverJson(HttpServletRequest request,GlobalErrorInfoException e){
        ErrorInfoInterface errorInfo = e.getErrorInfo();
        ResultMsg msg = new ResultMsg(errorInfo);
        return msg;
    }
}
