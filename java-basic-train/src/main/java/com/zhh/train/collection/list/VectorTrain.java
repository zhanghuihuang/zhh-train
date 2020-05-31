package com.zhh.train.collection.list;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * vector开始于jdk1.0
 * ArrayList与它的代码逻辑基本一致,只是vector的方法用synchronized修饰,线程安全
 * @date : 2020/5/24 4:17 下午
 */
public class VectorTrain {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        /**
         *
         * add插入尾部逻辑:插入到数组size+1的位置:
         * 判断是否需要扩容:
         * 先计算一个最小容量:如果当前是空list,最小扩容量10,如果不是,最小扩容量size+1
         * 明确扩容大小:判断如果已经超过了数组的大小,进行扩容
         * 新容量=旧容量+旧容量>>1
         * 如果新容量比需要的容量还小,就取需要的容量
         * 插入指定索引位置:插入的索引只能是0 <= index <= size,不然会报IndexOutOfBoundsException数组越界
         */
        for (int i = 1; i <= 20; i++) {
            vector.add(i + "");
        }
        vector.add(20, "30");
        System.out.println(vector.size());
        /**
         * for(x:x):底层是一个迭代器,当调用时,new Itr(),并取到当前对list的修改次数,
         * 如果在迭代过程中,发生对list修改,会报ConcurrentModificationException
         */
        for (String s : vector) {
            //vector.add(s);
        }
        /**
         * 想要循环时,对集合操作,需要获取迭代器,通过迭代器操作
         */
        Iterator<String> iterator = vector.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            //iterator.remove();
        }
        /**
         * 数组迭代器,对普通迭代器扩展:可以找前置元素和后置元素,可以修改元素
         */
        ListIterator<String> listIterator = vector.listIterator();
        while (listIterator.hasNext()) {
            listIterator.next();
            listIterator.hasPrevious();
            listIterator.set("1");
        }
        /**
         * remove删除操作
         * 根据元素删除:先找到元素下标,在remove
         * 根据下标索引删除
         *  两者底层都是利用{@code System.arraycopy(Object src,  int srcPos,Object dest, int destPos,int length)}
         *  这是一个本地native方法
         *  从源数组src的下标srcPos位置开始复制length长的数据到目标数据dest的下标位置destPos开始
         */
        vector.remove(0);
        vector.remove("10");

        /**
         * 操作子集:
         * 对子集的操作会影响arrayList
         */
        List<String> subList = vector.subList(0, 10);
        subList.add("10");

        /**
         * 流操作
         * 数据流底层就是获取到list的分离器ArrayListSpliterator
         * 由StreamSupport.stream把分离器包裹成ReferencePipeline.Head类获得Stream接口的真正实现类
         * forEach通过ReferencePipeline.Head的forEach方法
         * 判断是否并发流,不是会调用ArrayListSpliterator实现的forEachRemaining
         * 如果是调用其他处理
         */
        vector.stream().forEach(s -> {
            System.out.println(s);
        });
    }
}
