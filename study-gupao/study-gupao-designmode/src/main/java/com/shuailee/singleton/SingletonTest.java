package com.shuailee.singleton;

import java.util.concurrent.CountDownLatch;

public class SingletonTest {

    public static void main( String[] args )
    {
      new SingletonTest().mock();

    }


    int size=10000;
    CountDownLatch latch=new CountDownLatch(size);
    public void mock()
    {
        long start=System.currentTimeMillis();


        for (int i=0;i<size;i++){
            //System.out.println("创建线程："+i);
             new Thread(){

                 @Override
                 public void run() {
                     try{
                        //sleep(1000);
                         //必然会调用，可能会有很多线程同时去访问getInstance()
                         Object obj = RegisterBeanFactory.genBean("com.shuailee.factory.YiliImpl");
                         System.out.println(System.currentTimeMillis() + ":" + obj);

                     }catch (Exception e){
                         e.printStackTrace();
                     }
                     finally {
                         //每次启动一个线程，count --1 提交闭锁
                         latch.countDown();
                     }
                 }
             }.start();
        }
        try {
            // 阻塞
            // count = 0 就会释放所有的共享锁
            // 万箭齐发
            latch.await();
        }catch(Exception e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - start));
    }
}
