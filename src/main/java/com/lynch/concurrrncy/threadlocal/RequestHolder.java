package com.lynch.concurrrncy.threadlocal;

/**
 * Created by lynch on 2019-11-30. <br>
 * ThreadLocal 线程封闭
 **/
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
