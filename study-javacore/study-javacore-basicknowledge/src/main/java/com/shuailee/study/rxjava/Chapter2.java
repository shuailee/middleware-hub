package com.shuailee.study.rxjava;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @program: study-javacore
 * @description: Chapter2
 * @author: shuai.li
 * @create: 2020-07-06 14:03
 **/
public class Chapter2 {

    /**
     * debounce 去除发送频率过快的项
     */
    @Test
    public void debounceDemo() throws InterruptedException {

        // 去除发送间隔时间小于 500 毫秒的发射事件
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1); // skip
                TimeUnit.MILLISECONDS.sleep(400);
                observableEmitter.onNext(2); // deliver
                TimeUnit.MILLISECONDS.sleep(505);
                observableEmitter.onNext(3); // skip
                TimeUnit.MILLISECONDS.sleep(100);
                observableEmitter.onNext(4); // deliver
                TimeUnit.MILLISECONDS.sleep(605);
                observableEmitter.onNext(5); // deliver
                TimeUnit.MILLISECONDS.sleep(510);

                observableEmitter.onComplete();
            }
        })
                .debounce(500, TimeUnit.MILLISECONDS) //去除发送频率过快的项
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });

        TimeUnit.SECONDS.sleep(10);
    }


    /**
     * 每次订阅都会创建一个新的 Observable，并且如果没有被订阅，就不会产生新的 Observable。
     */
    @Test
    public void deferDemo() {

        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(1, 2, 3);
            }
        });

        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("defer : " + integer);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("defer : onError : " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("defer : onComplete");
            }
        });

    }

    /**
     * last 操作符仅取出可观察到的最后一个值，或者是满足某些条件的最后一项
     */
    @Test
    public void lastDemo() {
        Observable.just(1, 2, 3, 2, 3)
                .last(4)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("last : " + integer);
                    }
                });
    }

    /**
     * reduce 操作符每次用一个方法处理一个值，可以有一个 seed 作为初始值。
     * reduce((x,y) => x+y)
     *  支持一个 function 为两数值相加，所以应该最后的值是：1 + 2 = 3 + 3 = 6
     */
    @Test
    public void reduceDemo() {
        Observable.just(1, 2, 3).reduce(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    /**
     * scan 操作符作用和上面的 reduce 一致,区别在于reduce只关注结果得返回，scan会把每一次运算输出
     * scan((x,y) => x+y)
     * */
    @Test
    public void scanDemo() {
        // func 包装的是有返回值得方法
        Observable.just(1, 2, 3).scan(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    /**
     * window 按照指定要求划分窗口，将数据发送给不同的 Observable
     *
     * */
    @Test
    public void windowDemo() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS) // 间隔一秒发一次
                .take(15) // 最多接收15个
                .window(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Observable<Long>>() {
                    @Override
                    public void accept(Observable<Long> longObservable) throws Exception {
                        System.out.println("sub divide begin ...");
                        longObservable.subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                System.out.println(aLong);
                            }
                        });
                    }
                });

        TimeUnit.SECONDS.sleep(25);
    }
}
