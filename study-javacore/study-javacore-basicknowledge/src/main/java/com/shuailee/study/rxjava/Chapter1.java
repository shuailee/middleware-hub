package com.shuailee.study.rxjava;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: study-javacore
 * @description: Chapter1
 * @author: shuai.li
 * @create: 2020-07-06 10:28
 **/
public class Chapter1 {

    /**
     * just将传入的参数依次发送出来
     */
    @Test
    void justdemo() {

        Observable.just("A", "B", "C", "D", "E").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    /**
     * skip跳过指定数量的事件
     */
    @Test
    void skipdemo() {

        Observable.just("A", "B", "C", "D", "E")
                .skip(2)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }

    /**
     * distinct 去重
     */
    @Test
    void distinctdemo() {
        Observable.just("A", "A", "A", "B", "B", "C")
                .distinct()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });

    }

    /**
     * filter 过滤
     */
    @Test
    void filterDemo() {
        Observable.just(1, 23, 11, -1, 3, 4).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer > 10;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });

    }

    /**
     * 将 Observable 中发射的数据按 skip (步长) 分成最大不超过 count 的 buffer，然后生成一个新的Observable 输出
     * buffer 操作符接受两个参数，buffer(count,skip)
     * count 每一次取得事件数量有多少
     * skip 每一次取完以后，下次要跳过得事件数量（从第一次开始取得地方计数）
     */
    @Test
    void bufferDemo() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8)
                .buffer(3, 2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        for (Integer integer : integers) {
                            System.out.println(integer);
                        }
                        System.out.println("accept over");
                    }
                });
    }


    /**
     * timer 相当于一个定时任务，延时任务执行。在1.x中它还可以执行循环间隔逻辑，在2.x中 循环间隔执行逻辑被交给了interval
     * 默认为新线程
     */
    @Test
    void timerDemo() throws InterruptedException {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("事件触发" + new Date());
                    }
                });

        // 由于事件是在新线程里执行，所以主线程要被阻塞到事件线程结束才能看到它的执行结果
        Thread.sleep(3000);
    }


    /**
     * interval 操作符用于间隔时间执行某个操作，其接受三个参数，分别是第一次发送延迟，间隔时间，时间单位。
     * 默认为新线程
     */
    @Test
    void intervalDemo() throws InterruptedException {
        // 第一次延迟了 3 秒后接收到，后面每次间隔了 2 秒执行一次
        System.out.println("start at:" + new Date());
        Observable.interval(3, 2, TimeUnit.SECONDS)
                /*.subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())*/
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("interval :" + aLong + " at " + new Date());
                    }
                });

        TimeUnit.SECONDS.sleep(20000);
        System.out.println("over");

    }

    /**
     * 让订阅者在接收到数据之前干点别的事情
     */
    @Test
    void doOnNext() {
        Observable.just(1, 2, 3, 4).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("记日志" + integer);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }
}
