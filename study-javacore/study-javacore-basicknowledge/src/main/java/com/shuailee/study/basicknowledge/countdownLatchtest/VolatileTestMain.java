package com.shuailee.study.basicknowledge.countdownLatchtest;



/**
 * @program: study-javacore
 * @description:
 * @author: shuai.li
 * @create: 2019-03-23 18:03
 **/
public class VolatileTestMain {



    public static void main(String[] args) {
        final Counter counter = new Counter();
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    counter.inc();
                }
            }).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter);
    }

}


 class Counter {

    /**
     * volatile 只能保证可见性，禁止指令重排，不能保证原子性
     * 执行++操作的时候字节码指令分了多步走，所以多线程情况下会有线程安全问题
     * */
    private volatile int count = 0;

    public void inc() {
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }
    @Override
    public String toString() {
        return "[count=" + count + "]";
    }
}
