package com.atgugui.cloud.alibaba.service;

import com.atguigu.cloudaliaba.pojo.Payment;

public interface PaymentService {
    int addPayment(Payment payment);

    Payment selectPaymentById(Integer id);
}
