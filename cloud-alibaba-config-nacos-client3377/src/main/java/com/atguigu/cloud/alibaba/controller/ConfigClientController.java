package com.atguigu.cloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${config.info}")
    String configInfo;
    @GetMapping("/config/getNacosConfig")
    public String getConfigInfo(){

        System.out.println("从nacos读取配置文件"+configInfo);
        return "从nacos读取配置文件"+configInfo;
    }
}
