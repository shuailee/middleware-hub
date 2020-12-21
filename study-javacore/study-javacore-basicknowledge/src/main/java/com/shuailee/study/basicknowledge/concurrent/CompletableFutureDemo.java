package com.shuailee.study.basicknowledge.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @program: study-javacore
 * @description:
 * @create: 2019-09-10 21:15
 **/
public class CompletableFutureDemo {
    /**
     * 用多线程优化性能，其实不过就是将串行操作变成并行操作，将原来串行执行的业务改为异步执行。异步化是并行方案得以实施的基础。
     * <p>
     * 使用CompletableFuture 对比 Futuretask 的优势：
     * 1 无需手工维护线程，没有繁琐的手工维护线程的工作，给任务分配线程也不用关注
     * 2 语义清晰，代码简洁，几乎所有代码都与业务逻辑相关
     * runAsync()、supplyAsync()、thenCombine()
     */
    public static void main(String[] args) {

        new CompletableFutureDemo().simpledemo();
    }


    public void simpledemo() {

        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("f1 洗水壶...");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("f1 烧开水...");
            sleep(15, TimeUnit.SECONDS);
        });


        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("f2 洗茶壶...");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("f2 洗茶杯...");
            sleep(5, TimeUnit.SECONDS);

            System.out.println("f2 取茶叶...");
            sleep(1, TimeUnit.SECONDS);
            return "白茶";
        });


        CompletableFuture<String> f3 = f1.thenCombine(f2, (T, tf) -> {

            System.out.println("T1: 拿到茶叶..." + tf);
            System.out.println("T1: 泡茶...");
            return " 上茶:" + tf;

        });

        System.out.println(f3.join());

    }


    void sleep(int i, TimeUnit u) {
        try {
            u.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
