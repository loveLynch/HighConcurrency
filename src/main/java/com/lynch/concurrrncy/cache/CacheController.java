package com.lynch.concurrrncy.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lynch on 2019-11-30.
 **/
@RestController
@RequestMapping("/cache")
public class CacheController {
    @Autowired
    private RedisClient redisClient;

    @GetMapping("/set")
    public String set(@RequestParam("k") String k, @RequestParam("v") String v) throws Exception {
        redisClient.set(k, v);
        return "SUCCESS";


    }

    @GetMapping("/get")
    public String get(@RequestParam("k") String k) throws Exception {
        return redisClient.get(k);
    }
}
