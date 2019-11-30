高并发（HighConcurrency)

#并发与高并发
1.并发：多个线程操作相同的资源，保证线程安全，合理使用资源

2.高并发：服务能同时处理很多请求，提高程序性能

#线程安全性
1.原子性：提供了互斥访问，同一时刻只能有一个线程来对它进行操作

atomic包
cas算法
synchronized
Lock
2.可见性：一个线程对驻内存的修改可以及时的被其他线程观察到

不可见的原因：
- 线程交叉执行
- 重排序结合线程交叉执行
- 共享变量更新后的值没有在工作内存与主存间及时更新
3.有序性：一个线程观察其他线程中的指令执行顺序，由于指令重排的存在，该观察的结果一般杂乱无序
happens-before原则（8条）


synchonized 原子性、可见性、有序性
volatile 可见性（通过加入内存屏障和禁止重排序优化来实现）、有序性
lock 原子性、有序性

#atomic包中的类


# 安全发布对象
1.发布与逸出
- 发布对象：使一个对象能被当前范围之外的代码所使用
- 对象逸出：一种错误的发布。当一个对象还没有构造完成时，就使它被其他线程所见。
2.安全发布对象-四种方法
- 在初始化函数中初始化一个对象引用
- 将对象的引用保存到volatile类型域或者AtomicReference对象中
- 将对象的引用白存到某个正确构造对象的final类型域中
- 将对象的引用保存到一个由锁保护的域中
单例的正确创建
枚举模式


#线程安全策略

1.不可变对象
- 对象创建以后其状态就不能修改
- 对象所有域都是final类型
- 对象是正确创建的（在对象创建期间，this引用没有3逸出）
参考String类型

final关键字：类、方法、变量
    修饰类：不能被继承
    修饰方法：1。锁定方法不被继承类修改；2。效率
    修饰变量：基本数据类型变量（数值初始化后不能修改）、引用类型变量（引用初始化之后不能引用到其他对象）
Collections.unmodifiableXXX:Collection、List、Set、Map
Guava:ImmutableXXX:Collection、List、Set、Map

2.线程封闭
把对象封装到一个线程里面，只有该线程获取该对象
- Ad-hoc线程封闭：程序控制实现，最糟糕，忽略
- 堆栈封闭：局部变量（方法内部），无并发问题
- ThreadLocal线程封闭:特别好的线程封闭方法

3.线程不安全类与写法
- StringBuilder -> StringBuffer(synchronized，线程安全) 性能
- SimpleDateFormat -> JodaTime(线程安全)
- ArrayList,HashSet,HashMap等Collections
- 先检查再执行：if(condition(a)) {handle(a)} (注意是否多线程共享，原子性，加锁)

4.同步容器
- ArrayList -> Vector,Stack
- HashMap -> HashTable(key,value不能为null)
- Collection.synchronizedXXX(List、Set、Map)

5.并发容器（J.U.C)
- ArrayList -> CopyOnWriteArrayList(读不加锁，写加锁 缺点：1.数组，消耗内存;2.不能用于实时读的场景，它是最终一致性)
- HashSet、TreeSet -> CopyOnWriteArraySet、ConcurrentSkipListSet(jdk6)
- HashMap、TreeMap -> ConcurrentHashMap、ConcurrentSkipListMap(key有序、支持更高的并发)


#高并发之扩容思路
