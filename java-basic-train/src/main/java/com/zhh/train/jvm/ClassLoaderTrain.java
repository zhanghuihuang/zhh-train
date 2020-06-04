package com.zhh.train.jvm;

import sun.misc.Launcher;

import java.net.URL;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 * 类加载器知识点:
 * 1.加载器类型
 * 根类加载器:BootstrapClassLoader
 * 用来加载java的核心类库
 * 扩展类加载器:ExtClassLoader
 * 负责加载JRE的扩展目录，lib/ext或者由java.ext.dirs系统属性指定的目录中的JAR包的类。
 * 由Java语言实现，父类加载器为null。
 * 应用加载器:AppClassLoader,父类加载器ExtClassLoader
 * 负责在JVM启动时加载来自Java命令的-classpath选项
 * java.class.path系统属性
 * CLASSPATH换将变量所指定的JAR包和类路径
 * 2.类的加载过程
 * 加载:将class加载到内存,并创建一个java.lang.Class对象
 * 链接:验证-准备-解析
 *      验证:校验是否符合java语言规范
 *      准备:为静态变量分配内存和java默认值设置
 *      解析:将类的二进制数据中的符号引用替换成直接引用
 * 初始化:为类的静态变量赋予正确的初始值,代码编写的初始值
 * 3.类的加载机制
 * 全盘委派
 * 双亲委派
 * 缓存机制
 * 4.什么是双亲委派机制
 * 加载一个类时,都是先让自己的父加载器去加载,只有在父加载加载不到时,才自己去加载
 * 5.为什么要采用双亲委派机制
 * java类随着它的类加载器具有优先层次关系,避免重复加载
 * 安全因素考虑:防止java的核心API被篡改
 * </pre>
 * @since : 2020/5/31 1:16 下午
 */
public class ClassLoaderTrain {

    public static void main(String[] args) {
        //类加载器的层级
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        System.out.println(systemClassLoader.getParent());
        System.out.println(systemClassLoader.getParent().getParent());
        System.out.println("==========================================");
        /**
         * 根加载器加载的jar
         */
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL);
        }
    }
}
