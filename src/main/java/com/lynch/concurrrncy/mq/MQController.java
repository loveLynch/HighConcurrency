package com.lynch.concurrrncy.mq;

import com.lynch.concurrrncy.mq.rabbitmq.RabbitMQClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lynch on 2019-12-01.
 **/
@RestController
@RequestMapping("/mq")
public class MQController {
    @Resource
    private RabbitMQClient rabbitMQClient;

//    @Resource
//    private KafkaSender kafkaSender;

    @GetMapping("/send")
    public String send(@RequestParam("message") String message) {
        rabbitMQClient.send(message);
//        kafkaSender.send(message);
        return "success";
    }
}
