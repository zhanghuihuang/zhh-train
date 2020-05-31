package com.zhh.train.algorithm.bit;

/**
 * @author : page
 * @project : zhh-train
 * @description : 位运算训练
 * @date : 2020/5/13 12:49 下午
 */
public class BitCalculation {
    public static void main(String[] args) {
        System.out.println(Math.pow(2, 2) * Math.pow(2, 4));
        System.out.println(Math.pow(2, 4) * Math.pow(2, 4));
        System.out.println(-31 << 2);
        System.out.println(90 & 15);
    }

    private static void and() {
        //  0000 0100
        //  0000 0101
        //& 0000 0100
        System.out.println(4 & 5);
        //清零操作
        System.out.println(12431241 & 0);
        //判断奇偶数
        System.out.println((124326 & 1) == 0 ? "偶数" : "奇数");
        //取指定位数,只要与一个指定位数都是1的相与,如29=0001 1101保留2位数0000 0011 = 3
        System.out.println(29 & 3);
        //规律总结,10进制转2进制
        //1 2 4 8 16 32 64 128 256 512 1024 2048 4096
    }

    private static void or() {
        //  0010 0100
        //  0001 0110
        //| 0011 0110
        System.out.println(48 | 22);
        //两个偶数或运算,取
    }

    /**
     * 异或:0^0=0  0^1=1  1^0=1  1^1=0
     */
    private static void diffOr() {
        int a = 8;   // 0000 1000
        int b = 10;  // 0000 1010
        a ^= b;      //a = a^b  0000 0010
        b ^= a;      //b = b^a  0000 1000
        a ^= b;      //a = a^b  0000 1010
        System.out.println(a + "," + b);
    }

}
