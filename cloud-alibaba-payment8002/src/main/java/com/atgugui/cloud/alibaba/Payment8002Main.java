package com.atgugui.cloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Payment8002Main {
    public static void main(String[] args) {
        SpringApplication.run(Payment8002Main.class,args);
    }
}
