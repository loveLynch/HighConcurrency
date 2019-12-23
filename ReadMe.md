高并发（HighConcurrency)

# 并发与高并发
1.并发：多个线程操作相同的资源，保证线程安全，合理使用资源

2.高并发：服务能同时处理很多请求，提高程序性能

# 线程安全性
1.原子性：提供了互斥访问，同一时刻只能有一个线程来对它进行操作
- atomic包
- cas算法
- synchronized
-Lock

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

# atomic包中的类
1.原子更新基本类型
- AtomicBoolean：以原子更新的方式更新boolean；
- AtomicInteger：以原子更新的方式更新Integer;
- AtomicLong：以原子更新的方式更新Long；

2.原子更新数组类型
- AtomicIntegerArray：原子更新整型数组中的元素；
- AtomicLongArray：原子更新长整型数组中的元素；
AtomicReferenceArray：原子更新引用类型数组中的元素

3.原子更新引用类型
- AtomicReference：原子更新引用类型；
- AtomicReferenceFieldUpdater：原子更新引用类型里的字段；
- AtomicMarkableReference：原子更新带有标记位的引用类型；

4.原子更新字段类型
- AtomicIntegerFieldUpdater：原子更新整型字段类；
- AtomicLongFieldUpdater：原子更新长整型字段类；
- AtomicStampedReference：原子更新引用类型，这种更新方式会带有版本号。而为什么在更新的时候会带有版本号，是为了解决CAS的ABA问题；


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


# 线程安全策略

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

# J.U.C之AQS
1.AbstractQueuedSynchronizer - AQSI
- 使用Node实现FIFO队列，可以用于构建锁或者其他同步队列装置的基础框架
- 利用一个int类型表示状态
- 使用方法是继承
- 子类通过继承并通过实现它的方法管理其状态{acquire和release}的方法操纵状态
- 可以同时实现排它锁和共享锁（共享、独占）

2.CountDownLatch
- await()方法
- 一个或多个线程都等待
- countDown() -> 0 时，处于等待的线程就去执行

3.Semaphore
- 控制某个资源可以同时访问的个数
- 用于有限访问的资源

4.CyclicBarrier 
- 多个线程相互等待，直到都满足后才能执行后续的操作
- 计数器（+1）reset可重置计算
- 循环屏障
- 多线程计算数据，最后合并

5.ReentrantLock与锁
- ReentrantLock（可重入锁）和synchronized区别
  - 可重入锁 （都是）
  - 锁的实现 （ReentrantLock -> JDK, synchronized -> JVM）
  - 性能的区别
  - 功能的区别（ReentrantLock手动加锁与释放，synchronized简洁）
- ReentrantLock独有的功能
  - 可指定是公平锁还是非公平锁
  - 提供了一个Condition类，可以分组唤醒需要唤醒的线程
  - 提高能够中等待锁的线程的机制，lock.lockInterruptibly()
 



# J.U.C组件拓展
1.FutureTask
- Callable与Runnable接口对比
- Future接口
- FutureTask类

2.Fork/Join 框架
- 大任务分成若干个小任务，最终汇总—>大任务
- 工作窃取算法（并行）
- 

# 线程调度-线程池

# 多线程并发拓展


# 高并发之扩容思路
1.垂直扩容（纵向扩展）：提高系统部件能力
单个能力——服务器性能

2.水平扩容（横行扩展）：增加更多系统成员来实现
更多的处理（成员)——服务器集群

> 扩容：数据库

- 读操作扩展：memcache、redis、CDN等缓存
- 写操作扩展：Cassandra、Hbase等

# 高并发之缓存思路
1.缓存特征
- 命中率：命中数/（命中数+没有命中数）
- 最大元素（空间）
- 清空策略：FIFO、LFU（最少使用）、LRU（最近最少使用策略)、过期时间、随机等

2.缓存命中率影响因素
- 业务场景和业务需求
- 缓存的设计（粒度和策略）[通常情况下粒度越小,命中率越高]
- 缓存容量和基础设施

3.缓存分类和应用场景
- 本地缓存：编程实现（成员变量、局部变量、静态变量)、Guava Cache
- 分布式缓存：Memcache、Redis


> 缓存-Guava Cache
> - 类似ConcurrentHashMap思路
> - LocalCache —> LRU算法


> 缓存-Memcache
> - 客户端路由 ->（映射到）服务器
> - 一致性hash算法（0～2^32-1)
> - slab_class->slab->page->chuck
> - 特点

> 缓存-Redis
> - 数据类型（String\hash\list\set\sorted set)
> - 编码方式（raw\int\ht\zipmap\linkedlist\ziplist\intset)
> - 数据备份（master->slave)
> - 内存、也可持久化到磁盘
> - 原子性 （单线程）
> - 场景（最新数据、排行榜、精准过期、唯一性、计数、实时系统等）

4.高并发场景下缓存常见问题
- 缓存一致性    
- 缓存并发问题
- 缓存穿透问题
- 缓存雪崩现象

> 缓存一致性 [缓存与数据库一种，缓存主从数据一致]
> 1) 更新数据库成功 -> 更新缓存失败 -> 数据不一致
> 2) 更新缓存成功 -> 更新数据库失败 -> 数据不一致
> 3) 更新数据库成功 -> 淘汰缓存失败 -> 数据不一致
> 4) 淘汰缓存成功 -> 更新数据库失败 -> 查询缓存miss

> 缓存并发问题
> - 锁机制

> 缓存穿透问题 [高并发访问没有被命中，去数据库访问]
> - 缓存空对象，字段标示或null -> 适合命中不高，频繁更新的数据，注意时效性
> - 单独过滤处理，对所有可能为空的数据进行访问拦截 -> 适合命中不高，更新的不频繁的问题

> 缓存雪崩现象【缓存大面积失效，都访问到数据库]
> - 设置不同过期时间
> - 限流、降级、熔断
> - 多级缓存
> - 压力测试


# 高并发之消息队列
1.特性
- 业务无关：只做消息分发
- FIFO：先投递先到达
- 灾害：节点的动态增删和消息的持久化
- 性能：吞吐量提升，系统内部通信效率提升

2.使用消息队列的原因 

->【生产】和【消费】的速度或稳定性等因素不一致

3.消息队列好处
- 业务解耦
- 最终一致性   [强一致性与最终一致性]
- 广播
- 错峰与流控

4.消息队列举例
- Kafka
- RabbitMQ
- Rocketmq

> 消息队列 -> Kafka
> - 快速持久化
> - 高吞吐，分布式系统，负载均衡
> - producer topic partition consumer


> 消息队列 -> RabbitMq
> - producer exchange (type、RoutingKey) queue consumer
> - 管理界面: http://localhost:15672/


# 高并发之应用拆分思路

> 应用拆分 -> 股票系统[用户信息、开户、股票行情、交易、订单、、、]
- 交易中心[股票买入与卖出]
- 账户中心[流程处理、定时任务]
- 用户中心[基础数据维护]
- 行情中心[定时任务、流程处理]
- 通知中心[短信、邮件、推送]

1.原则
- 业务优先
- 循序渐进
- 兼顾技术：重构、分层
- 可靠测试

2.思考
- 应用之间通信：RPC(dubbo等)、消息队列
- 应用之间数据库设计：每个应用都有独立的数据库
- 避免事务操作跨应用

3.应用拆分
- 服务化 Dubbo
- 微服务 Spring Cloud9

# 高并发之应用限流思路
1.算法
- 计数器法
- 滑动窗口
- 漏桶算法
- 令牌桶算法

2.计数器算法
- counter
- 计数与时间
- 可能突发请求过多导致崩

3.滑动窗口
- counter
- 进一步时间划分

4.漏桶算法（Leaky Bucket)
- 请求速度（恒定的速率）
- 桶溢出
- 水流出孔限制

5.令牌桶算法（Token Bucket)
                 N
> Arrival -> Full? -> Queue -> Processor  -> Departure
>           | Y
>           Discard
- 很好解决临界问题


# 高并发之服务降级与服务熔断
1.服务降级

> 当前请求不过来，返回默认的响应

> 分类：
- 自动降级：超时、失败次数、故障、限流
- 人工降级：秒杀、双11大促等

2.服务熔断 -> 过载保护
> 服务过载时进行保护，防止整个系统崩溃

3.服务降级与服务熔断
> 共性:
> - 目的：可用性和可靠性
> - 最终表现：服务暂时不可达
> - 粒度：服务级别或更细
> - 自治

> 区别：
> - 触发原因
> - 管理目标
> - 实现方式

4.服务降级考虑的问题
- 核心服务、非核心服务
- 是否支持降级，降级策略
- 业务放通场景，策略

5.Hystrix
- 在通过第三方客户端访问（通常是通过网络）依赖服务出现高延迟或失败时，为系统提供保护和控制
- 在分布式系统中防止级联失败
- 快速失败（fail fast)同时能快速恢复
- 提供失败回退（fallback)和优雅的服务降级机制



# 高并发之数据库切库分库分表

1.数据库瓶颈
- 单个数据量太大（1T～2T)：多个库
- 单个数据率服务器压力过大、读写瓶颈：多个库
- 单个表数据量过大：分表

2.数据库切库
- 切库的基础与实际运用：读写分离
- 自定义注解完成数据库切库 - 代码实现 https://blog.csdn.net/shmily_lsl/article/details/79894298

3.数据库分表
- 什么时候分表
- 横向（水平）分表与纵向（垂直）分表
- 数据库分表：mybatis分表插件 shardbatis 2.0

# 高并发之高可用手段
- 任务调度系统分布式：elastic-job + zookeeper
- 主备切换：apache curator + zookeeper 分布式锁实现
- 监控报警机制









