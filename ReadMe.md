高并发
#并发与高并发
1.并发：多个线程操作相同的资源，保证线程安全，合理使用资源

2.高并发：服务能同时处理很多请求，提高程序性能

#线程安全性
1.原子性：提供了互斥访问，同一时刻只能有一个线程来对它进行操作
2.可见性：一个线程对驻内存的修改可以及时的被其他线程观察到
3.有序性：一个线程观察其他线程中的指令执行顺序，由于指令重排的存在，该观察的结果一般杂乱无序

#atomic包中的类