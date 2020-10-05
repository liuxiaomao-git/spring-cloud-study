package com.atguigu.cloud.alibaba.enu;

public enum RabbitMqEnm {

    DIRECT_QUEUE("directQueue"),
    TOPIC_QUEUE_ONE("topicQueueOne");

    private String name;

    RabbitMqEnm(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
