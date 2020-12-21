package com.shuailee.study.basicknowledge.countdownLatchtest;

import java.util.concurrent.CountDownLatch;

/**
 * @program: study-javacore
 * @description: 数据库链接健康检查
 * @author: shuai.li
 * @create: 2018-10-26 15:32
 **/
public class DatabaseHealthCheckerImpl extends BaseHealthChecker {
    /**
     * desc: 构造函数初始化
     *
     * @param
     * @param latch
     * @parameter: [serviceName, latch]
     * @return:
     */
    public DatabaseHealthCheckerImpl(CountDownLatch latch) {
        super("DatabaseHealthCheckerImpl", latch);
    }

    @Override
    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP 健康检查完成");
    }
}
