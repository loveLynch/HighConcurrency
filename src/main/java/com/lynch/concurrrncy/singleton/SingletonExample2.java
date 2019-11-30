package com.lynch.concurrrncy.singleton;

/**
 * Created by lynch on 2019-11-29. <br>
 * 饿汉模式
 * 单例在类装载的时候被创建
 **/
//线程安全
public class SingletonExample2 {

    //私有构造函数,但当构造函数中加载太多处理，会造成资源浪费与加载过慢
    private SingletonExample2() {

    }

    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    //静态的工厂方法
    public static SingletonExample2 getInstance() {
        return instance;

    }
}
