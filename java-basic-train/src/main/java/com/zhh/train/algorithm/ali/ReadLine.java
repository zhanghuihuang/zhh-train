package com.zhh.train.algorithm.ali;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * <p>
 * 第二题：
 * 每次调用recv（）返回一段字符，其中有零、一或多个换行符；
 * 请写出下面的read（）函数，要求使用recv（）来实现，每次调用read（）时返回一行；
 * 在这个范例程序里，要求打印出来三行：
 * 123
 * 45
 * 6789abcde
 * @date : 2020/5/13 7:56 下午
 */

public class ReadLine {

    //已知函数
    static int count = 0;


    private String recv() {
        switch (++count) {
            case 1:
                return "123\n45\n6789";
            case 2:
                return "abc";
            case 3:
                return "de\n";
        }
        return "\n";
    }

    /*
    请观察以上已知函数，每次调用返回一段字符，其中有零、一或多个换行符；
    请写出下面的read（）函数，要求使用上述的recv（）来实现，每次调用read（）时返回一行；
    在这个范例程序里，要求打印出来三行：
    123
    45
    6789abcde
    */
    public static void main(String[] args) {
        ReadLine rl = new ReadLine();
        System.out.print(rl.read());
        System.out.print(rl.read());
        System.out.print(rl.read());
    }

    static String buffer = null;

    //TODO 补充代码
    public String read() {
        String recv = recv();
        if (count == 1) {
            int endIndex = recv.indexOf("\n");
            String substring = recv.substring(0, endIndex + 1);  // 123\n
            buffer = recv.substring(endIndex + 1);  //45\n6789\n
            return substring;
        } else if (count == 2) {
            int endIndex = buffer.indexOf("\n");
            String result = buffer.substring(0, endIndex + 1);  //45\n
            buffer = buffer.substring(endIndex + 1) + recv;  //6789\nabc\n
            return result;
        } else {
            buffer = buffer + recv;  //6789\nabc\nde\n\n
            return buffer.replaceAll("\n", "");
        }
    }
}

