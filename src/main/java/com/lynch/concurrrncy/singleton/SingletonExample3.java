package com.lynch.concurrrncy.singleton;

/**
 * Created by lynch on 2019-11-29. <br>
 * 懒汉模式
 * 单例在第一次使用的时候才被创建
 **/
//线程安全
//不推荐
public class SingletonExample3 {

    //私有构造函数
    private SingletonExample3() {

    }

    //单例对象
    private static SingletonExample3 instance = null;

    //静态的工厂方法，不推荐，性能开销过大
    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;

    }
}
