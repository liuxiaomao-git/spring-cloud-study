package com.atgugui.cloud.alibaba.mapper;

import com.atguigu.cloudaliaba.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    int addPayment(Payment payment);

    Payment selectPaymentById(Integer id);
}
