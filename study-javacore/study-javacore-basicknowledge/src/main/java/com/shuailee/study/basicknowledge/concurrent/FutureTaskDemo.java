package com.shuailee.study.basicknowledge.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo  {
    public static void main(String[] args) {
        /**
        * FutureTask类实际上是同时实现了Runnable和Future接口，由此才使得其具有Future和Runnable双重特性。通过Runnable特性，
        * 可以作为Thread对象的target，而Future特性，使得其可以取得新创建线程中的call()方法的返回值。
        * */

        // 创建MyCallable对象
        Callable<Integer> myCallable = new MyCallable();
        //使用FutureTask来包装MyCallable对象
        FutureTask<Integer> ft = new FutureTask<Integer>(myCallable);
        //FutureTask对象作为Thread对象的target创建新的线程
        Thread thread = new Thread(ft);
        //线程进入到就绪状态
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            /**
            * 通过ft.get()方法获取子线程call()方法的返回值时，当子线程此方法还未执行完毕，ft.get()方法会一直阻塞，直到call()方法执行完毕才能取到返回值。
            * */
            //取得新创建的线程中的call()方法返回的结果
            int sum = ft.get();
            System.out.println("task运行结果,sum = " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}


class MyCallable implements Callable<Integer>{

    // 与run()方法不同的是，call()方法具有返回值
    public Integer call() throws Exception{
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            //System.out.println(Thread.currentThread().getName() + " " + i);
            sum += i;
        }
        return sum;
    }

}