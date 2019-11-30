package com.lynch.concurrrncy.singleton;

/**
 * Created by lynch on 2019-11-29. <br>
 * 饿汉模式
 * 单例在类装载的时候被创建
 **/
//线程安全
public class SingletonExample6 {

    //私有构造函数,但当构造函数中加载太多处理，会造成资源浪费与加载过慢
    private SingletonExample6() {

    }

    //单例对象
    private static SingletonExample6 instance = null;

    //静态代码块，注意位置与执行顺序
    static {
        instance = new SingletonExample6();
    }


    //静态的工厂方法
    public static SingletonExample6 getInstance() {
        return instance;

    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
