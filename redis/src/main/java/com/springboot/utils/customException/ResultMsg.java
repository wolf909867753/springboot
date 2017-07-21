package com.springboot.utils.customException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglu-jf on 17/7/20.
 */
public class ResultMsg implements Serializable{
    private String code;
    private String message;
    private List<Object> lists = new ArrayList();

    public ResultMsg(CustomerException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public ResultMsg(List<Object> lists) {
        this.code = ExceptionEnum.SUCCESS.getCode();
        this.message = ExceptionEnum.SUCCESS.getMessage();
        this.lists = lists;
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

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return "ResultMsg{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", lists=" + lists +
                '}';
    }
}
