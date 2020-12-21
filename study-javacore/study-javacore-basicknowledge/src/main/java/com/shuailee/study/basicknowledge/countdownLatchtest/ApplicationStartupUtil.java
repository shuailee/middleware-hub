package com.shuailee.study.basicknowledge.countdownLatchtest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @program: study-javacore
 * @description: 这个类是一个主启动类，它负责初始化闭锁，然后等待，直到所有服务都被检测完。
 * @author: shuai.li
 * @create: 2018-10-26 19:49
 **/
public class ApplicationStartupUtil {

    //需要检查的服务列表
    private static List<BaseHealthChecker> services;

    //同步工具类
    private static CountDownLatch latch;

    private ApplicationStartupUtil() {
    }

    /**
     * 饿汉单例模式
     * */
    private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();
    public static ApplicationStartupUtil getInstance()
    {
        return INSTANCE;
    }

    /**
     * 模拟了一个应用程序启动类，它开始时启动了n个线程类，这些线程将检查外部系统并通知闭锁，
     * 并且启动类一直在闭锁上等待着。一旦验证和检查了所有外部服务，那么启动类恢复执行
     *
     * */

    public static boolean checkExternalServices() throws Exception
    {
        //设置监控线程数（因为一共有两个服务，所以开启两个线程分别检测）
        latch=new CountDownLatch(2);
        services = new ArrayList<BaseHealthChecker>();
        services.add(new CacheHealthCheckerImpl(latch));
        services.add(new DatabaseHealthCheckerImpl(latch));

        for(final BaseHealthChecker v : services)
        {
            Thread t=new Thread(v);
            t.start();
        }
        //now wait till all thread services are checked
        //等待所有的服务启动线程提交完
        latch.await();

        //所有服务是否全部启动成功
        for(final BaseHealthChecker v : services)
        {
            if( ! v.isServiceUp())
            {
                return false;
            }
        }
        return true;
    }
}
