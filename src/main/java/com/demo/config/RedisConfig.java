package com.demo.config;

import com.demo.receiver.RedisReceiver1;
import com.demo.receiver.RedisReceiver2;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter1, MessageListenerAdapter listenerAdapter2) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // 将监听器1绑定Topic交换机
        container.addMessageListener(listenerAdapter1, new PatternTopic("onlineCustomerService"));
        // 将监听器2添加Topic交换机
        container.addMessageListener(listenerAdapter2, new PatternTopic("onlineCustomerService"));
        return container;
    }

    // 绑定消息处理器1
    @Bean
    MessageListenerAdapter listenerAdapter1(RedisReceiver1 receiver) {
        System.out.println("消息处理器1 -> 启动");
        return new MessageListenerAdapter(receiver, "onMessage");
    }

    // 绑定消息处理器2
    @Bean
    MessageListenerAdapter listenerAdapter2(RedisReceiver2 receiver) {
        System.out.println("消息处理器2 -> 启动");
        return new MessageListenerAdapter(receiver, "onMessage");
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

}
