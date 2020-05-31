package com.zhh.train.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * ArrayList基于数组实现
 * 1.实现了List和RandomAccess随机访问接口
 * 2.根据下标查找速度快
 * 3.插入效率不高:如果遇到需要扩容的情况,会进行数组的复制
 * 4.可以动态扩容,默认初始化容量10
 * @date : 2020/5/22 9:40 下午
 */
public class ArrayListTrain {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        int a = 0144;   //java中八进制以0开始:1*8^2+4*8^1+4*8^0=64+32+4=100
        int b = 0123;   //1*8^2+2*8^1+3*8^0=64+16+3=83
        int c = 0x123; //java中十六进制以0x开始:1*16^2+2*16^1+3*16^0=256+32+3=291
        System.out.println(a + "," + b + "," + c);
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
            arrayList.add(i + "");
        }
        arrayList.add(20, "30");
        System.out.println(arrayList.size());
        /**
         * for(x:x):底层是一个迭代器,当调用时,new Itr(),并取到当前对list的修改次数,
         * 如果在迭代过程中,发生对list修改,会报ConcurrentModificationException
         */
        for (String s : arrayList) {
            //arrayList.add(s);
        }
        /**
         * 想要循环时,对集合操作,需要获取迭代器,通过迭代器操作
         */
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            //iterator.remove();
        }
        /**
         * 数组迭代器,对普通迭代器扩展:可以找前置元素和后置元素,可以修改元素
         */
        ListIterator<String> listIterator = arrayList.listIterator();
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
        arrayList.remove(0);
        arrayList.remove("10");

        /**
         * 操作子集:
         * 对子集的操作会影响arrayList
         */
        List<String> subList = arrayList.subList(0, 10);
        subList.add("10");

        /**
         * 流操作
         * 数据流底层就是获取到list的分离器ArrayListSpliterator
         * 由StreamSupport.stream把分离器包裹成ReferencePipeline.Head类获得Stream接口的真正实现类
         * forEach通过ReferencePipeline.Head的forEach方法
         * 判断是否并发流,不是会调用ArrayListSpliterator实现的forEachRemaining
         * 如果是调用其他处理
         */
        arrayList.stream().forEach(s -> {
            System.out.println(s);
        });
    }

}
