package com.lynch.concurrrncy.threadlocal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lynch on 2019-11-30. <br>
 **/
@RestController
@RequestMapping("/threadLocal/")
public class ThreadLocalController {

    @GetMapping("/test")
    public Long test() {
        return RequestHolder.getId();
    }
}
