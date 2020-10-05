package com.atguigu.cloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.cloudaliaba.pojo.CommonResult;
import com.atguigu.cloudaliaba.pojo.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${service-url.nacos-payment-provider}")
    private String PAYMENT_URL;

    @GetMapping("/consumer/payment/addPayment")
    public CommonResult addPayment(Payment payment){
//        try {
//            TimeUnit.MICROSECONDS.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return restTemplate.postForObject(PAYMENT_URL+"/payment/addPayment",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getPayment/{id}")
    @SentinelResource("consumer/payment/getPayment")
    public CommonResult addPayment(@PathVariable("id") Long id){

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return restTemplate.getForObject(PAYMENT_URL+"/payment/getPayment/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/testHospKey")
    @SentinelResource("testHospKey")
    public String testHospKey(String param1,String param2){

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return "testHospKey";
    }

}
