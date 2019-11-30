package com.lynch.concurrrncy.singleton;

/**
 * Created by lynch on 2019-11-29. <br>
 * 懒汉模式-》双重同步锁单例
 * 单例在第一次使用的时候才被创建
 **/
//线程安全，双重同步锁单例+volatile限制指令重排
public class SingletonExample5 {

    //私有构造函数
    private SingletonExample5() {

    }


    //单例对象
    private volatile static SingletonExample5 instance = null;

    //静态的工厂方法
    public static SingletonExample5 getInstance() {
        if (instance == null) {//双重检测机制
            synchronized (SingletonExample5.class) {//同步锁
                if (instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;

    }
}
