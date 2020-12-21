package com.shuailee.study.basicknowledge.concurrent;

import com.google.common.util.concurrent.*;


import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *  https://blog.ganzhiqiang.wang/2019/04/01/%E5%BC%82%E6%AD%A5%E2%80%94%E2%80%94ListenalbeFuture%E7%9A%84%E4%BD%BF%E7%94%A8%E6%80%BB%E7%BB%93/#more
 * */
public class ListenalbeFutureDemo {

    /**
     * Future + Callable 方式实现异步编程（同时执行多个任务），获取结果十分不方便，只能通过阻塞或者轮询的方式获取结果，
     * 阻塞的方式显然与我们异步编程的初衷相违背，而且轮询的方式也很消耗的CPU资源，计算结果也不能及时拿到。
     * 面对上面这种情况，为什么不采用一种类似观察者模式的方式，当结果结算完成后实时通知到监听任务：
     * 1 guava包提供了拓展Future：ListenableFuture和SettableFuture，辅助类 Futures
     * 2 JDK 8中也提供类似ListenableFuture的CompletableFuture接口,
     * 3 SettableFuture可以认为是一种异步转同步工具，可以它在指定时间内获取ListenableFuture的计算结果
     * 4 JdkFutureAdapters该方法可以将JDK Future转成ListenableFuture
     * */


    ExecutorService threadPool = Executors.newFixedThreadPool(5);

    /**
     * Guava为了支持自己的Listerner模式，新建了一种ExecutorService，叫做ListeningExecutorService，可以使用MoreExecutor类创建新建ExecutorService
     * 也可以使用MoreExecutors.listeningDecorator包装方法装饰 原来的java线程池
     */
    //ListeningExecutorService executorService = MoreExecutors.newDirectExecutorService();
    // 装饰自定义的线程池返回
    ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ListenalbeFutureDemo demo = new ListenalbeFutureDemo();
        // Future + Callable
        // demo.runFutureTask();

        //添加监听（addListener）
        demo.addListenDemo();

        // 分别执行每个future 回调
        // demo.runListenFuture();

        // 合并多个Future（Futures.allAsList）执行，用一个回调监听
        //demo.runAllfuture();

        // 对结果进行转换
        //demo.transformtest();

    }



    // ListenableFuture接口扩展自Future接口，并添加了一个新方法 addListener，该方法是给异步任务添加监听
    public void addListenDemo() {
        ListenableFuture<String> future = executorService.submit(() -> "hello world");
        future.addListener(() -> {
            System.out.println("执行addListenDemo");
            doSomeThing(future);
        }, executorService);

    }
    private void doSomeThing(ListenableFuture<String> future) {
        try {
            Thread.sleep(1000);
            System.out.println("获取任务结果：" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }




    /**
     * 2  合并多个Future（Futures.allAsList）：
     * <p>
     * 如果需要同时获取多个Future的值，可以使用Futures.allAsList
     * 如果任何一个Future在执行时出现异常，都会只执行onFailure()方法，如果想获取到正常返回的Future，
     * 可以使用Futures.successfulAsList方法，该方法会将失败或取消的Future的结果用null来替代，不会让程序进入onFailure()方法
     */
    public void runAllfuture() {
        ListenableFuture<Integer> futureListen1 = getListenableFuture();
        ListenableFuture<Integer> futureListen2 = getListenableFuture();
        // 如果任何一个Future在执行时出现异常，都会只执行onFailure()方法
        //ListenableFuture<List<Object>> list = Futures.allAsList(futureListen1,futureListen2);
        // 获取到正常返回的Future,该方法会将失败或取消的Future的结果用null来替代，不会让程序进入onFailure()方法
        ListenableFuture<List<Object>> success = Futures.successfulAsList(futureListen1, futureListen2);
        Futures.addCallback(success, new FutureCallback<List<Object>>() {
            @Override
            public void onSuccess(List<Object> objects) {
                // reduce 求和
                int result = objects.stream().map(s -> (int) s)
                        .reduce(0, (a, b) -> a + b);
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        }, executorService);
    }

    /**
     * 创建一个ListenableFuture任务
     */
    private ListenableFuture getListenableFuture() {
        ListenableFuture<Integer> futureListen = executorService.submit(() -> {
            int result = 1;
            result++;
            System.out.println("listenFuture任务开始" + Thread.currentThread().getId());
            Thread.sleep(1000);
            return result;
        });
        return futureListen;
    }


    /**
     * 3 如果需要对返回值做处理，可以使用Futures.transform方法，它是同步方法，另外还有一个异步方法Futures.transformAsync
     */
    public void transformtest() {
        // 原Future
        ListenableFuture<String> future3 = executorService.submit(() -> "hello, future");
        // 同步转换
        ListenableFuture<Integer> future5 = Futures.transform(future3, String::length, executorService);
        // 异步转换
        //ListenableFuture<Integer> future6 = Futures.transformAsync(future3, input -> Futures.immediateFuture(input.length()), executorService);

        Futures.addCallback(future3, new FutureCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println(s);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        }, executorService);


        Futures.addCallback(future5, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        }, executorService);
    }

    //region --Future + Callable

    /**
     * Future + Callable 方式实现异步编程
     */
    public void runFutureTask() throws ExecutionException, InterruptedException {

        Future<Integer> task1 = futureTask();
        Future<Integer> task2 = futureTask();
        task1.get();
        task2.get();
    }

    /**
     * Future + Callable 方式实现异步编程
     */
    public Future<Integer> futureTask() {
        Future<Integer> task = threadPool.submit(() -> {
            int result = 0;
            try {
                System.out.println("任务开始" + Thread.currentThread().getId());
                Thread.sleep(1000);
                System.out.println("hello word, wait 1s" + Thread.currentThread().getId());
                for (int i = 0; i < 10; i++) {
                    result += i;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        });
        return task;
    }
//endregion
}
