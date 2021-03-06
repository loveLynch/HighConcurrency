package com.lynch.concurrrncy.publish;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by lynch on 2019-11-29. <br>
 **/
@Slf4j
//对象逸出
//线程不安全
//----一种错误的发布。当一个对象还没有构造完成时，
//就使它被其他线程所见。
public class Escape {


    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {

        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
