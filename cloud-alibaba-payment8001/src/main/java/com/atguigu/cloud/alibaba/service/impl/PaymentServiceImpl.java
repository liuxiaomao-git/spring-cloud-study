package com.atguigu.cloud.alibaba.service.impl;

import com.alibaba.nacos.common.util.UuidUtils;
import com.atguigu.cloud.alibaba.config.RabbitMqConfig;
import com.atguigu.cloud.alibaba.constant.RabbitMqConstant;
import com.atguigu.cloud.alibaba.enu.RabbitMqEnm;
import com.atguigu.cloud.alibaba.mapper.PaymentMapper;
import com.atguigu.cloud.alibaba.service.PaymentService;
import com.atguigu.cloudaliaba.pojo.Payment;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentMapper paymentMapper;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Override
    public int addPayment(Payment payment) {
        return paymentMapper.addPayment(payment);
    }

    @Override
    public Payment selectPaymentById(Integer id) {
        String msgId = UuidUtils.generateUuid();
    //    rabbitTemplate.setChannelTransacted(true);
   //     rabbitTemplate.convertAndSend(RabbitMqConstant.DIRECT_EXCHANGE_NAME, RabbitMqConstant.ROUTING_KEY_ONE,"payment 被查询了一次：："+8001,new CorrelationData(msgId));
        //System.out.println(1/0);

        return paymentMapper.selectPaymentById(id);
    }
}
