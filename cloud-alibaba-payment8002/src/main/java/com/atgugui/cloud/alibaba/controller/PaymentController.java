package com.atgugui.cloud.alibaba.controller;

import com.atgugui.cloud.alibaba.service.PaymentService;
import com.atguigu.cloudaliaba.pojo.CommonResult;
import com.atguigu.cloudaliaba.pojo.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverport;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    PaymentService paymentService;
    @PostMapping("/payment/addPayment")
    public CommonResult addPayment(@RequestBody Payment payment){
        int result = paymentService.addPayment(payment);
        if(result > 0){
            return  new CommonResult(200,"插入成功");

        }else{
            return new CommonResult(500,"插入失败");
        }

    }
    @GetMapping("/payment/getPayment/{id}")
    // 超时或异常报错 调用方法，超时时间设置
//    @HystrixCommand(fallbackMethod = "hystrixTimeoutHandler" ,commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")
//    })
    public CommonResult selectPaymentById(@PathVariable("id") Integer id){
        System.out.println("查询payment******"+ serverport);
        Payment payment = paymentService.selectPaymentById(id);
        //   System.out.println(1/0);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(payment != null){
            return  new CommonResult<Payment>(200,"查询成功"+"serverport:"+serverport,payment);

        }else{
            return  new CommonResult<Payment>(500,"查询成功",null);
        }
    }
    @GetMapping("/payment/discovery")
    public  void discovery(){
        List<String> services = discoveryClient.getServices();

        services.forEach(s -> System.out.println("service:::::::::::"+s));
        String description = discoveryClient.description();
        System.out.println("description"+description);

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        for (ServiceInstance instance : instances) {
            String host = instance.getHost();
            System.out.println("host:"+host+"port:"+instance.getPort()+"url:"+instance.getUri());
        }
    }
    @GetMapping("/payment/hystrixTestOK/{id}")
    public String hystrixTestOK(@PathVariable("id")String id){
        return "线程池：  "+Thread.currentThread().getName()+"   hystrixTestOK" +id+"\t"+"O(∩_∩)O";
    }
    @GetMapping("/payment/hystrixTestException/{id}")
    public String hystrixTestException(@PathVariable("id")String id){
        int time = 3;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：  "+Thread.currentThread().getName()+"   hystrixTestException" +id+"\t"+"耗时：："+time;
    }

    public CommonResult<Payment> hystrixTimeoutHandler(Integer id){
        return  new CommonResult<Payment>(501,"调用超时方法"+"serverport:"+serverport);
    }


}
