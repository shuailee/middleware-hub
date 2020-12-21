package com.shuailee.study.basicknowledge.countdownLatchtest;

import java.util.concurrent.CountDownLatch;

/**
 * @program: study-javacore
 * @description: 缓存服务健康检查
 * @author: shuai.li
 * @create: 2018-10-26 15:33
 **/
public class CacheHealthCheckerImpl extends BaseHealthChecker{

    public CacheHealthCheckerImpl (CountDownLatch latch){
        super("CacheHealthCheckerImpl",latch);
    }

    @Override
    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(7000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP 健康检查完成");
    }
}
