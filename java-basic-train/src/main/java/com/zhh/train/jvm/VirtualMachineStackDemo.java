package com.zhh.train.jvm;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/5/5 4:43 下午
 */
public class VirtualMachineStackDemo {

    public static int count = 0;
    public static String CODE1 = "CODE";
    public static String CODE2 = new String("CODE");
    private String code1 = "CODE";
    private String code2 = new String("CODE");

    public int compute() {
        int a = 10;
        int b = 20;
        int c = (a + b) * 2;
        return c;
    }

    public static void main(String[] args) {
        VirtualMachineStackDemo demo = new VirtualMachineStackDemo();
        int add = demo.compute();
        System.out.println(add);
        System.out.println(CODE1 == demo.code1);
        System.out.println(CODE1.equals(demo.code1));
        System.out.println(CODE1 == CODE2);
        System.out.println(CODE1.equals(CODE2));
        System.out.println(demo.code2 == CODE2);
        System.out.println(demo.code2.equals(CODE2));
    }
}
