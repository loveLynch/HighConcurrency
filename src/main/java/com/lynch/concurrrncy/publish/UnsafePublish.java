package com.lynch.concurrrncy.publish;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Created by lynch on 2019-11-29. <br>
 **/
@Slf4j
//线程不安全
//发布对象
//-----使一个对象能被当前范围之外的代码所使用
public class UnsafePublish {
    private String[] states = {"a", "b", "c"};


    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));


    }
}
