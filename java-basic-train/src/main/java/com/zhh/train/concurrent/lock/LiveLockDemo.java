package com.zhh.train.concurrent.lock;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * 活锁
 * 是什么?
 * 两个或两个以上的线程因为资源的推让,而造成获取不到锁
 * 线程1可以使用资源，但它很礼貌，让其他线程先使用资源，
 * 线程2也可以使用资源，但它很绅士，也让其他线程先使用资源。
 * 这样你让我，我让你，最后两个线程都无法使用资源
 * <p>
 * 场景:
 * 比如生活中喝汤,只有一个公共勺子,每个人用勺子时,如果
 * 原因
 * <p>
 * 怎么办
 * @since : 2020/5/31 12:14 下午
 */

public class LiveLockDemo {

    //定义一个勺子，ower 表示这个勺子的拥有者
    static class Spoon {
        Diner owner;//勺子的拥有者

        //获取拥有者
        public String getOwnerName() {
            return owner.getName();
        }

        //设置拥有者
        public void setOwner(Diner diner) {
            this.owner = diner;
        }

        public Spoon(Diner diner) {
            this.owner = diner;
        }

        //表示正在用餐
        public void use() {
            System.out.println(owner.getName() + " use this spoon and finish eat.");
        }
    }

    //定义一个晚餐类
    static class Diner {
        public Diner(boolean isHungry, String name) {
            this.isHungry = isHungry;
            this.name = name;
        }

        private boolean isHungry;//是否饿了
        private String name;//定义当前用餐者的名字

        public String getName() {//获取当前用餐者
            return name;
        }

        //可以理解为和某人吃饭
        public void eatWith(Diner spouse, Spoon sharedSpoon) {
            try {
                synchronized (sharedSpoon) {
                    while (isHungry) {
                        //当前用餐者和勺子拥有者不是同一个人，则进行等待
                        while (!sharedSpoon.getOwnerName().equals(name)) {
                            sharedSpoon.wait();
                            //System.out.println("sharedSpoon belongs to" + sharedSpoon.getOwnerName())
                        }
                        //spouse此时是饿了，把勺子分给他，并通知他可以用餐
                        if (spouse.isHungry) {
                            System.out.println("I am " + name + ", and my " + spouse.getName() + " is hungry, I should give it to him(her).\n");
                            sharedSpoon.setOwner(spouse);
                            sharedSpoon.notifyAll();
                        } else {
                            //用餐
                            sharedSpoon.use();
                            sharedSpoon.setOwner(spouse);
                            isHungry = false;
                        }
                        Thread.sleep(500);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println(name + " is interrupted.");
            }
        }
    }

    public static void main(String[] args) {
        final Diner husband = new Diner(true, "husband");//创建一个丈夫用餐类
        final Diner wife = new Diner(true, "wife");//创建一个妻子用餐类
        final Spoon sharedSpoon = new Spoon(wife);//创建一个勺子，初始状态并由妻子持有

        //创建一个 线程，由丈夫进行用餐
        Thread h = new Thread() {
            @Override
            public void run() {
                //表示和妻子用餐，这个过程判断妻子是否饿了，如果是，则会把勺子分给妻子，并通知她
                husband.eatWith(wife, sharedSpoon);
            }
        };
        h.start();

        //创建一个 线程，由妻子进行用餐
        Thread w = new Thread() {
            @Override
            public void run() {
                //表示和妻子用餐，这个过程判断丈夫是否饿了，如果是，则会把勺子分给丈夫，并通知他
                wife.eatWith(husband, sharedSpoon);
            }
        };
        w.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        h.interrupt();
        w.interrupt();

        try {
            h.join();//join()方法阻塞调用此方法的线程(calling thread)，直到线程t完成，此线程再继续；通常用于在main()主线程内，等待其它线程完成再结束main()主线程。
            w.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
