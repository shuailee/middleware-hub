package com.shuailee.study.basicknowledge.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @program: study-javacore
 * @description:
 * @author: shuai.li
 * @create: 2019-12-04 21:45
 **/
@Slf4j
public class TestExecutor {
    private Executor executor;

    /**
     * 设置执行器
     *
     * @param executor
     */
    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    /**
     * 用执行器执行多个任务
     */
    public void executeTasks() {
        //连续执行10个任务
        for (int i = 0; i < 10; i++) {
            executor.execute(new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务");
            }));
        }
    }



    public static void main(String[] args) {
        TestExecutor testExecutor = new TestExecutor();
        // Noncompliant
       /* testExecutor.setExecutor(Executors.newFixedThreadPool(2));
        testExecutor.executeTasks();*/

        ExecutorService service = testExecutor.getFixedThreadPool();
        for (int i = 0; i < 100; i++) {
            service.execute(new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务");
            }));
        }
        service.shutdown();

    }


    /**
     * demo1 手工创建线程池
     * */
    public ExecutorService  getFixedThreadPool(){
        // 动态获取线程数 cpu核数
        // Runtime.getRuntime().availableProcessors() * 2

        //线程池名称
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();

        ExecutorService fixedThreadPool = new ThreadPoolExecutor(
                2,
                20,
                200L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                namedThreadFactory);
        log.info("serviceThreadPool active count: {}");
        return fixedThreadPool;
    }




    /**
     * demo2 手工创建线程池
     * */

    private static final int CORE_POOL_SIZE = 10;
    private static final long KEEP_ALIVE_TIME = 60L;
    private static final int SERVICE_THREAD_MAXIMUM_POOL_SIZE = 1000;
    private static final String SERVICE_THREAD_NAME_FORMAT = "service-thread-pool-%d";

    public ExecutorService getServiceThreadPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(SERVICE_THREAD_NAME_FORMAT).build();

        ThreadPoolExecutor poolExecutor =
                new ThreadPoolExecutor(CORE_POOL_SIZE, SERVICE_THREAD_MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                        new SynchronousQueue<>(), threadFactory, new ThreadPoolExecutor.DiscardPolicy());

        poolExecutor.prestartAllCoreThreads();

        log.info("serviceThreadPool active count: {}", poolExecutor.getActiveCount());

        return poolExecutor;
    }


}