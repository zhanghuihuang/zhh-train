package com.zhh.train.algorithm.ali;

/**
 * <pre>
 *
 * </pre>
 *
 * @author zhanghuihuang
 * @since 2020/7/9 1:53 下午
 */
public class Demo1 {

    public static void main(String[] args) {
        //System.out.println(gab(1000, 80));
        hanNuoTa(3, "A", "B", "C");
    }

    /**
     * 求两个数的最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public static int gab(int a, int b) {
        return a % b == 0 ? b : gab(b, a % b);
    }

    private static int count = 0;

    /**
     * 汉诺塔问题
     * 有ABC三个竹竿,A上面有从大到小的圆盘N个,现在把A上的圆盘移动到C上,也必须从大到小
     */
    public static void hanNuoTa(int n, String from, String buffer, String to) {
        if (n == 1) {
            System.out.printf("第%d步,把%d盘子从%s--→%s\n", ++count, n, from, to);
            return;
        }
        //先把from的n-1个盘子移动到buffer
        hanNuoTa(n - 1, from, to, buffer);
        //把from剩下的1个移动到to
        System.out.printf("第%d步,把%d盘子从%s--→%s\n", ++count, n, from, to);
        //处理剩下的n-1个,把buffer的n-1个移动会from
        hanNuoTa(n - 1, buffer, from, to);
    }
}
