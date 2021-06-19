package com.demo.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisReceiver1 implements MessageListener {

    /**
     * 监听消息
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info(new String(message.getChannel()) + " :\t" + new String(message.getBody()));
    }
}
