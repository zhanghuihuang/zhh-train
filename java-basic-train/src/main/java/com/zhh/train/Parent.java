package com.zhh.train;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/5/30 4:18 下午
 */
public class Parent {

    static{
        System.out.println("Parent 静态方法块");
    }

    {
        System.out.println("Parent 对象方法块");
    }

    public Parent(){
        System.out.println("Parent 无参构造");
    }

    public Parent(int i){
        System.out.println("Parent 有参构造:"+i);
    }

}
