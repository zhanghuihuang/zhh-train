package com.zhh.train;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * 1.静态方法块只有在类被加载的时候执行一次
 * 2.对象方法块每次new对象都会执行一次
 * 3.构造方法
 * =====================================
 * 调父类的静态方法块
 * 调子类的静态方法块
 * 调父类对象的对象方法块
 * 调父类的构造函数(子类new时,默认都会调用父类的构造函数,可以不指定,也可以指定)
 * 调子类对象的对象方法块
 * 调子类的构造函数
 * @date : 2020/5/30 4:18 下午
 */
public class Child extends Parent {

    static {
        System.out.println("Child 静态方法块");
    }

    {
        System.out.println("Child 对象方法块");
    }

    public Child() {
        super(1);
        System.out.println("Child 无参构造");
    }

    public Child(int i) {
        System.out.println("Child 有参构造:" + i);
    }

    public static void main(String[] args) {
        Child child = new Child();
        System.out.println("======================");
    }

}
