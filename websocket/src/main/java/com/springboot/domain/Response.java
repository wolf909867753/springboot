package com.springboot.domain;

/**
 * Created by wanglu-jf on 17/8/30.
 * 服务器向浏览器发送的此类消息。
 */
public class Response {

    private String responseMessage;

    public Response(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public String toString() {
        return "Response{" +
                "responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
