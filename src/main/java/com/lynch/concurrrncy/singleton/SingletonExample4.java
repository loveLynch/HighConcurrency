package com.lynch.concurrrncy.singleton;

/**
 * Created by lynch on 2019-11-29. <br>
 * 懒汉模式-》双重同步锁单例
 * 单例在第一次使用的时候才被创建
 **/
//线程不一定安全，指令重排
public class SingletonExample4 {

    //私有构造函数
    private SingletonExample4() {

    }

    //cpu
    //1。memory = allocate() 分配对象的内存空间
    //2。ctorInstance() 初始化对象
    //3。instance= memory 设置instance指向刚分配的内存

    //JVM和cpu优化，发生了指令重排
    //1。memory = allocate() 分配对象的内存空间
    //3。instance= memory 设置instance指向刚分配的内存
    //2。ctorInstance() 初始化对象
    //--------->双重检测机制
    //-------------多线程
    //-------------创建多对象---不一定安全





    //单例对象
    private static SingletonExample4 instance = null;

    //静态的工厂方法
    public static SingletonExample4 getInstance() {
        if (instance == null) {//双重检测机制
            synchronized (SingletonExample4.class) {//同步锁
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;

    }
}
