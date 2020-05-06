package com.zhh.train.concurrent.blocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author : page
 * @project : zhh-train
 * @description : 阻塞队列示例
 * BlockingQueue属于集合Collection的一种
 * @date : 2020/5/5 5:19 下午
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println("1241121");
        }, "线程1");
        thread1.start();
        Thread thread = new Thread(() -> {
            System.out.println("1241");
        }, "线程2");
        thread.start();
        thread.join();


        System.out.println("结束主线程");
    }

    private static void testBlockingTimeout() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2, TimeUnit.SECONDS));
        //1 System.out.println(blockingQueue.offer("d", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        //2 System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
    }

    /**
     * 测试超过容量或从空队列获取会阻塞的插入\删除元素的一组方法
     */
    private static void testBlocking() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        //1 blockingQueue.put("d");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //2 System.out.println(blockingQueue.take());
    }

    /**
     * 测试超过容量或从空队列获取会正常有返回值的插入\删除\获取队首元素的一组方法
     */
    private static void testReturnValue() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        //1 System.out.println(blockingQueue.offer("d"));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //2 System.out.println(blockingQueue.poll());
        //3 System.out.println(blockingQueue.peek());
    }

    /**
     * 测试超过容量或从空队列获取会抛异常的插入\删除\获取队首元素的一组方法
     */
    private static void testThrowException() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //1 System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //2 System.out.println(blockingQueue.remove());
        //3 System.out.println(blockingQueue.element());
    }
}
