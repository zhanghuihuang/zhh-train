参考地址:
[https://blog.csdn.net/qq_22222499/article/details/79060495][]
[https://www.cnblogs.com/wenxiaofei/p/9853682.html][]

谈谈你对数据库事务的理解?
回答思路:事务概念→事务四大特性→并发五大问题→隔离四个级别→默认级别
概念:数据库中的事务是作为单个逻辑工作单元的一系列操作.通俗讲就是一个工作任务细分多个步骤,这些步骤哪个失败,都会导致工作任务完不成失败.

事务满足四大特性:
- 原子性(Atomicity):所有步骤要么成功要么失败
- 一致性(Consistency):事务前后,数据总额一致
- 隔离性(Isolation):事务在提交前,对其他事务不可见
- 持久性(Durability):事务提交后,对数据的修改将是永久的

并发五大问题: https://blog.csdn.net/cmmchenmm/article/details/82774703
脏读(dirty read):A事务读取了B事务尚未提交的更改数据，并且在这个数据基础上进行操作
不可重复读(unrepeatable read):同一个事务中,因为其他事务的更新,导致对同一个数据读取到不同的值
幻读(phantom read):同一个事务中,因为其他事务的新增或删除,导致数据一会儿可以读到,一会儿读不到
第一类更新丢失(first lost updates):A事务回滚,覆盖掉B事务已提交的数据
第二类更新丢失(second lost updates):A事务提交,覆盖掉B事务已提交的数据

隔离四个级别:
                            更新丢失    脏读  不可重复读   幻读
读未提交(Read Uncommitted):    ✔        ✔      ✔       ✔️
读已提交(Read Committed):      ✔        ❌      ✔       ✔
可重复读(Repeatable Read):     ❌        ❌      ❌       ✔  
串行读(Serializable):          ❌        ❌      ❌       ❌

默认隔离级别:REPEATABLE-READ

MySQL事务隔离级别和Spring事务关系介绍
https://blog.csdn.net/a837199685/article/details/54563740

MySQL常见两种存储引擎的区别?
回答思路:通过锁级别,索引,事务

谈谈你对MySQL数据库索引的认识?
索引的数据结构
B+树和Hash有什么区别和使用场景
聚集索引和非聚集索引的区别
索引的底层实现（B+树，为何不采用红黑树，B树）重点
B+树的实现及为什么使用B+树
哪些sql写法会引起索引失效

如果对sql查询进行优化?
慢查询
执行计划
常见sql编写原则

对于数据库锁的了解?

分库分表，主从复制，读写分离?

数据库三范式?

关系型数据库和非关系型数据库的区别?

为什么要使用数据库连接池?或者连接池的作用?

一条SQL语句执行得很慢的原因有哪些? 
https://www.cnblogs.com/kubidemanong/p/10734045.html

