package com.shuailee.study.basicknowledge.concurrent;

import java.util.concurrent.*;


/**
* 线程池的作用：线程池作用就是限制系统中执行线程的数量。
根 据系统的环境情况，可以自动或手动设置线程数量，达到运行的最佳效果；少了浪费了系统资源，多了造成系统拥挤效率不高。用线程池控制线程数量，其他线程排 队等候。
 一个任务执行完毕，再从队列的中取最前面的任务开始执行。若队列中没有等待进程，线程池的这一资源处于等待。当一个新任务需要运行时，如果线程池 中有等待的工作线程，
 就可以开始运行了；否则进入等待队列。

为什么要用线程池:
1.减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务。

2.可以根据系统的承受能力，调整线程池中工作线线程的数目，防止因为消耗过多的内存，而把服务器累趴下(每个线程需要大约1MB内存，线程开的越多，消耗的内存也就越大，最后死机)。

Java里面线程池的顶级接口是Executor，但是严格意义上讲Executor并不是一个线程池，而只是一个执行线程的工具。真正的线程池接口是ExecutorService。
ExecutorService： 真正的线程池接口。

ScheduledExecutorService： 能和Timer/TimerTask类似，解决那些需要任务重复执行的问题。

ThreadPoolExecutor： ExecutorService的默认实现。

ScheduledThreadPoolExecutor： 继承ThreadPoolExecutor的ScheduledExecutorService接口实现，周期性任务调度的类实现。

要配置一个线程池是比较复杂的，尤其是对于线程池的原理不是很清楚的情况下，很有可能配置的线程池不是较优的，因此在Executors类里面提供了一些静态工厂，生成一些常用的线程池。
*
* */


/**
 * @program: study-javacore
 * @description:
 * @author: shuai.li
 * @create: 2018-06-26 21:56
 **/
public class ThreadPoolDemo {
    //http://ifeve.com/java-7-concurrency-cookbook/

    public  static  void main(String [] args){
        ThreadPoolDemo th=new ThreadPoolDemo();
        th.createFixedThreadPool();

    }

    /**
     * desc: 创建定长线程池
     * @parameter: []
     * @return: void
     */
    public void createFixedThreadPool()
    {
        //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        //因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字
        //定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()。可参考PreloadDataCache。
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;

           /* fixedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });*/

            fixedThreadPool.execute(()->{
                try {
                    System.out.println(index);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
        }

        fixedThreadPool.shutdown();
    }

    /**
     * desc: createCachedThreadPool可缓存线程池
     * @parameter: []
     * @return: void
     */
    public void  createCachedThreadPool(){

        //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        //线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }

    /**
     * desc: createScheduledThreadPool创建一个定长线程池，支持定时及周期性任务执行
     * @parameter: []
     * @return: void
     */
    public void createScheduledThreadPool()
    {

        //创建一个定长线程池，支持定时及周期性任务执行。
        //延迟执行，表示延迟3秒执行
        // 动态获取线程数
        // Runtime.getRuntime().availableProcessors() * 2
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);


        //定期执行，表示延迟1秒后每3秒执行一次。
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    /**
     * desc: createSingleThreadExecutor创建一个单线程化的线程池
     * @parameter: []
     * @return: void
     */
    public void createSingleThreadExecutor(){

        //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
        //结果依次输出，相当于顺序执行各个任务。
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }




}


    class ThreadPool1 {

    // 动态获取线程数
    // Runtime.getRuntime().availableProcessors() * 2
    private final static ThreadPoolExecutor EMAIL_SEND_THREAD_POOL = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    public static void main(String [] args)
    {

        try {


            for(int i=0;i<100000;i++)
            {
                EMAIL_SEND_THREAD_POOL.execute(new Task(i));
                /*int s=i;
                EMAIL_SEND_THREAD_POOL.execute(()->process(s));*/
            }


        }catch (Exception ex){

        }
    }

    static class Task implements Runnable {

        int num;

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(num);
        }

        public Task(int num) {
            this.num = num;
        }
    }


    static void process(int i)
    {
        System.out.println(i);

    }
}
