package com.atgugui.cloud.alibaba.service.impl;

import com.atgugui.cloud.alibaba.mapper.PaymentMapper;
import com.atgugui.cloud.alibaba.service.PaymentService;
import com.atguigu.cloudaliaba.pojo.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentMapper paymentMapper;

    @Override
    public int addPayment(Payment payment) {
        return paymentMapper.addPayment(payment);
    }

    @Override
    public Payment selectPaymentById(Integer id) {
        return paymentMapper.selectPaymentById(id);
    }
}
