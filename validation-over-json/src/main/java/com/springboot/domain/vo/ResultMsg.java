package com.springboot.domain.vo;

import com.springboot.utils.customException.CustomerException;
import com.springboot.utils.exceptions.ErrorInfoInterface;
import com.springboot.utils.exceptions.ExceptionEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglu-jf on 17/7/19.
 */
public class ResultMsg implements Serializable {
    private String code ;
    private String message;
    private List<Object> list = new ArrayList<Object>();

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

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ResultMsg{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", list=" + list +
                '}';
    }

    public ResultMsg(ErrorInfoInterface error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    public ResultMsg(List<Object> lists){
        this.code = ExceptionEnum.SUCCESS.getCode();
        this.message = ExceptionEnum.SUCCESS.getMessage();
        this.list = lists;
    }

    public ResultMsg(CustomerException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public ResultMsg() {

    }
}
