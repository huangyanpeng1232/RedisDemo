package com.demo.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class SendController {

    @Resource
    StringRedisTemplate template;

    /**
     * 发布消息-官微发消息到客服时
     */
    @ResponseBody
    @RequestMapping("/sendMessage")
    public void sendMessage(String text) {
        template.convertAndSend("onlineCustomerService", text);
    }

}

