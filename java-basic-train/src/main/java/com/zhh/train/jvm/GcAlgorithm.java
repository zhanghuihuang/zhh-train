package com.zhh.train.jvm;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 *     垃圾回收算法
 *     1.引用计数
 *          需要维护引用计数器
 *          较难处理循环引用
 *     2.复制(Copying)
 *          把存活对象复制到一块空的内存区域,比较耗内存空间,用于年轻代区域
 *     3.标记清除(Mark-Sweep)
 *          用于老年代多一些,容易产生内存碎片
 *     4.标记整理(Mark-Sweep-Compact)
 *          用于老年代多一些,标记清除整理,没有内存碎片,但是需要移动对象的成本
 *     判断是否垃圾:引用计数和GC Root根可达性分析
 *     java中可以作为GC Root的对象
 *     1.java虚拟机栈中引用的对象
 *     2.静态属性引用的对象
 *     3.方法区中常量池引用的对象
 *     4.本地方法native中引用的对象
 *
 * </pre>
 * @since : 2020/5/31 4:50 下午
 */
public class GcAlgorithm {
}
