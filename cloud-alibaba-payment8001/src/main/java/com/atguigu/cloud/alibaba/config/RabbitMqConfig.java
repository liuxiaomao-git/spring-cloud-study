package com.atguigu.cloud.alibaba.config;

import com.atguigu.cloud.alibaba.constant.RabbitMqConstant;
import com.atguigu.cloud.alibaba.enu.RabbitMqEnm;
import lombok.val;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;

@Configuration
public class RabbitMqConfig{
    @Autowired
    CachingConnectionFactory cachingConnectionFactory;
    // 队列
    @Bean
    public Queue directQueue() {
        return new Queue(RabbitMqEnm.DIRECT_QUEUE.getName(), true, false, true);
    }
    // 队列
    @Bean
    public Queue topicQueueOne() {
        return new Queue(RabbitMqEnm.TOPIC_QUEUE_ONE.getName(), true);
    }
    // 交换机
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(RabbitMqConstant.DIRECT_EXCHANGE_NAME,true,false);
    }
    // 队列绑定交换机
    @Bean
    Binding directBinding(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(RabbitMqConstant.ROUTING_KEY_ONE);
    }
//    @Bean
//    CachingConnectionFactory cachingConnectionFactory(){
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
//        cachingConnectionFactory.setHost("192.168.15.100");
//        cachingConnectionFactory.setPort(5672);
//        cachingConnectionFactory.setUsername("guest");
//        cachingConnectionFactory.setPassword("guest");
//
//        cachingConnectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
//        return cachingConnectionFactory;
//    }



    @Bean
    RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setConfirmCallback((correlationData, ack , cause) -> {
            String id = correlationData.getId();
            if(ack){
                System.out.println(id+"消息发送成功！");
            }else{
                System.out.println(id+"消息发送给失败！");
            }
        });

        rabbitTemplate.setReturnCallback((msg, repCode, repText, exchange, routingkey) -> {
            System.out.println("发送失败！");
        });
        return rabbitTemplate;
    }
}
