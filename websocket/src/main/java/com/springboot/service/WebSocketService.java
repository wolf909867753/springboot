package com.springboot.service;

import com.springboot.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by wanglu-jf on 17/8/30.
 */
@Service
public class WebSocketService {
    @Autowired
    //使用SimpMessagingTemplate 向浏览器发送消息
    private SimpMessagingTemplate template;

    public void sendMessage() throws Exception{
        for(int i=0;i<10;i++){
            Thread.sleep(1500);
            template.convertAndSend("/topic/getResponse",new Response("Welcome,websocket!"+i));
            System.out.println("----------------------send:"+i);
        }
    }
}
