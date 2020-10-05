package com.atguigu.cloud.alibaba;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Payment8001Main {

    public static void main(String[] args) {
        SpringApplication.run(Payment8001Main.class,args);
    }
}
