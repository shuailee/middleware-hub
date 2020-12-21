package com.shuailee.study.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.io.Serializable;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @program: study-javacore
 * @description: ZipRxTest  https://www.jianshu.com/p/b39afa92807e
 * @author: shuai.li
 * @create: 2020-07-03 19:38
 **/
public class ZipRxTest {
    /**
     * zip 专用于配对合并事件,
     * 对两个被观察者发的出事件进行两两配对，最终配对出的 Observable 发射事件数目只和少的那个相同。
     * zip 组合事件的过程就是分别从发射器 A 和发射器 B 各取出一个事件来组合，并且一个事件只能被使用一次，
     * 组合的顺序是严格按照事件发送的顺序来进行的，所以上面截图中，可以看到，1 永远是和 A 结合的，2 永远是和 B 结合的。
     */
    @Test
    public void zip() {
        // 熟悉 1.x 的小伙伴一定都知道，我们在1.x 中是有 `Func1`，`Func2`.....`FuncN`的，但 2.x 中将它们移除，
        // 而采用 `Function` 替换了 `Func1`，采用 `BiFunction` 替换了 `Func 2..N`
        Observable.zip(getStringObservable(), getIntegerObservable(), new BiFunction<String, Integer, String>() {
            @Override
            public String apply(@NonNull String s, @NonNull Integer integer) throws Exception {
                return "string 事件：" + s + "integer 事件：" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("zip : accept : " + s + "\n");
            }
        });
    }


    private Observable<String> getStringObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext("A");
                    //System.out.println("String emit : A \n");
                    e.onNext("B");
                    //System.out.println("String emit : B \n");
                    e.onNext("C");
                    //System.out.println("String emit : C \n");
                }
            }
        });
    }

    private Observable<Integer> getIntegerObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext(1);
                    //System.out.println( "Integer emit : 1 \n");
                    e.onNext(2);
                    //System.out.println("Integer emit : 2 \n");
                    e.onNext(3);
                    //System.out.println("Integer emit : 3 \n");
                    e.onNext(4);
                    //System.out.println("Integer emit : 4 \n");
                    e.onNext(5);
                    //System.out.println( "Integer emit : 5 \n");
                }
            }
        });
    }


    /**
     * 把两个发射器连接成一个发射器
     * */
    @Test
    public void concat() {

        /**
         * `just(T...)`: 将传入的参数依次发送出来
         * `rom(T[])` / `from(Iterable)` : 将传入的数组或 `Iterable` 拆分成具体对象后，依次发送出来
         * */
        Observable.concat(Observable.just(1,2,3),Observable.just(4,5,6)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("accept"+integer);
            }
        });
    }

    /**
     * merge 的作用是把多个 Observable 结合起来，接受可变参数，也支持迭代器集合。
     * 注意它和 concat 的区别在于，不用等到 发射器 A 发送完所有的事件再进行发射器 B 的发送。
     * XXXXXXXXX 例子中 并没有体现
     * */
    @Test
    public void mergeDemo() throws InterruptedException {
        Observable.merge(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onNext(2);
                TimeUnit.MILLISECONDS.sleep(1000);
                observableEmitter.onNext(3);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }),Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(4);
                observableEmitter.onNext(5);
            }
        }))
                //.subscribeOn(Schedulers.newThread())
                //.observeOn(Schedulers.newThread())
                .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });

        TimeUnit.MILLISECONDS.sleep(5000);
    }
}
