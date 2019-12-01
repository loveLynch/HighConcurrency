package com.lynch.concurrrncy.mq.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by lynch on 2019-12-01.
 **/
@Component
@Slf4j
public class RabbitMQServer {

    @RabbitListener(queues = QueueConstants.TEST)
    public void receive(String message) {
        log.info("rabbitmq:{}", message);

    }
}
