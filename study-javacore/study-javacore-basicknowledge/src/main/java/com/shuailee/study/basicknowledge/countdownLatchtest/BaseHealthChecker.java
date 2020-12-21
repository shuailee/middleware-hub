package com.shuailee.study.basicknowledge.countdownLatchtest;

import java.util.concurrent.CountDownLatch;

/**
 * @program: study-javacore
 * @description: 这个类是一个健康检查线程抽象类，负责所有特定的外部服务健康的检测
 * @author: shuai.li
 * @create: 2018-10-26 14:30
 **/
public abstract class BaseHealthChecker implements Runnable{

    /**
     * CountDownLatch是一个同步工具类
     * */
    private CountDownLatch latch;
    /**
     * 服务名称
     * */
    private String serviceName;
    /**
     * 服务状态
     * */
    private boolean serviceUp;

    public CountDownLatch getLatch() {
        return latch;
    }

    public String getServiceName() {
        return serviceName;
    }

    public boolean isServiceUp() {
        return serviceUp;
    }

    /**
     * desc: 构造函数初始化
     * @parameter: [serviceName, latch]
     * @return:
     */
    public BaseHealthChecker(String serviceName, CountDownLatch latch)
    {
        super();
        this.latch = latch;
        this.serviceName = serviceName;
        this.serviceUp = false;
    }

    @Override
    public void run() {
        try {
            verifyService();
            serviceUp = true;
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            serviceUp = false;
        } finally {
            if(latch != null) {
                //其他N 个线程必须引用闭锁对象，因为他们需要通知CountDownLatch对象，他们已经完成了各自的任务。
                //这种通知机制是通过 CountDownLatch.countDown()方法来完成的
                latch.countDown();
            }
        }
    }

    public abstract void verifyService();
}
