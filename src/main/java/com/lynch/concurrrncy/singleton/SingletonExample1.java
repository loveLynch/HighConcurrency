package com.lynch.concurrrncy.singleton;

/**
 * Created by lynch on 2019-11-29. <br>
 * 懒汉模式
 * 单例在第一次使用的时候才被创建
 **/
//线程不安全
public class SingletonExample1 {

    //私有构造函数
    private SingletonExample1() {

    }

    //单例对象
    private static SingletonExample1 instance = null;

    //静态的工厂方法
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;

    }
}
