package com.lynch.concurrrncy.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * Created by lynch on 2019-11-30. <br>
 **/
@Configuration
public class RedisConfig {

    @Bean(name = "redisPool")
    public JedisPool jedisPool(@Value("${jedis.host}") String host, @Value("${jedis.port}") Integer port) {
        return new JedisPool(host, port);

    }

}
