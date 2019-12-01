package com.lynch.concurrrncy.mq.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lynch on 2019-12-01.
 **/
@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue queue() {
        return new Queue((QueueConstants.TEST));
    }

}
