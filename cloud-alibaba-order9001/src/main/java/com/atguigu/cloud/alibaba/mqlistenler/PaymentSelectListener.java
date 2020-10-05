package com.atguigu.cloud.alibaba.mqlistenler;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue(value = "directQueue", durable = "true",autoDelete = "true"))
public class PaymentSelectListener {

    @RabbitHandler
    public void receive(String message){
        System.out.println("PaymentSelectListener：：："+ message);

    }
}
