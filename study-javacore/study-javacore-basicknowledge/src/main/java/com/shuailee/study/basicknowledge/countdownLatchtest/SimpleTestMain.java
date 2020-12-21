package com.shuailee.study.basicknowledge.countdownLatchtest;

import java.util.concurrent.CountDownLatch;

/**
 * @program: study-javacore
 * @description:
 * @author: shuai.li
 * @create: 2018-10-26 20:35
 **/
public class SimpleTestMain {

    public static void main(String[] args) {
        SimpleTestMain testMain=new SimpleTestMain();
        try {
            testMain.process();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //同步工具类
    private static CountDownLatch latch;

    public void process() throws InterruptedException {
        System.out.println("say calling");
        int size = 100;

        /**
         * CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1。当计数器值到达0时，
         * 它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
         * 构造器中的计数值（count）实际上就是闭锁需要等待的线程数量。这个值只能被设置一次，而且CountDownLatch没有提供任何机制去重新设置这个计数值。
         * */
        latch=new CountDownLatch(size);

        for (int i = 0; i < size; i++) {

            Thread t = new Thread(new task(i));
            t.start();
        }
        /**
         * CountDownLatch的第一次交互是主线程等待其他线程。主线程必须在启动其他线程后立即调用CountDownLatch.await()方法。这样主线程的操作就会在这个方法上阻塞，
         * 直到其他线程完成各自的任务。
         * */
        latch.await();

        /**
         * 多线程情况下，say down 可能在前面输出，使用同步类工具以后，它会等待所有线程执行完成后输出
         * */
        System.out.println("say down");
    }


    class task implements Runnable {
        private int index;

        public task(int i){
            this.index=i;
        }

        @Override
        public void run() {
            try {

                Thread.sleep(10000);
                System.out.println("say hello:"+index);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if(latch != null) {
                    //每个线程必须引用闭锁对象，因为他们需要通知CountDownLatch对象，他们已经完成了各自的任务。
                    //这种通知机制是通过 CountDownLatch.countDown()方法来完成的
                    latch.countDown();
                    System.out.println("完成闭锁:"+index);
                }
            }
        }
    }
}
