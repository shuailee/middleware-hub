package com.shuailee.study.rxjava;

import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: study-javacore
 * @description: CreateRxTest
 * @author: shuai.li
 * @create: 2020-07-03 18:13
 **/
public class CreateRxTest {

    /**
     * Observable 被观察者，可观察对象 也是数据生产者
     * Observer 观察者 也是订阅者，消费者
     * Observable通过subscribe方法绑定观察者，通过unsubscrible解绑
     * Disposable 在调用subscribe方法的时候会返回一个Disposable对象
     */

    @Test
    public void create() {
        // create 创建一个被观察者 Observable（为事件的产生者，发射器）
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                //被观察者 发送普通事件
                System.out.println("Observable emit 1" + "\n");
                e.onNext(1);
                System.out.println("Observable emit 2" + "\n");
                e.onNext(2);
                System.out.println("Observable emit 3" + "\n");
                e.onNext(3);
                System.out.println("Observable emit 4" + "\n");
                e.onNext(4);
                // 在发射了数值 4 之后，直接调用了 e.onComlete()，虽然无法接收事件，但发送事件还是继续的
                e.onComplete();
                System.out.println("Observable emit 5" + "\n");
                e.onNext(5);
            }
            // 订阅方法，将被观察者和观察者连接起来（设计模式种观察者订阅被观察者是通过将观察者放入被观察者的通知集合中进行的）
        }).subscribe(new Observer<Integer>() {
            private int i;
            private Disposable mDisposable;

            // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe isDisposed: " + d.isDisposed() + "\n");
                mDisposable = d;
            }

            // RxJava的事件回调方法，针对普通事件
            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("onNext : value : " + integer + "\n");
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                    System.out.println("onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError : value : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete" + "\n");
            }
        });
    }


    @Test
    void map() {
        // map为发射的每一个事件应用函数操作，function类似于c# 中的Function匿名函数，有返回值，Action  无返回值
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onNext(2);
                observableEmitter.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "This is result" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("accept: " + s);
            }
        });
    }


    /**
     * 它可以把一个发射器 Observable 通过某种方法转换为多个 Observables，然后再把这些分散的 Observables装进一个单一的发射器 Observable。
     * 但有个需要注意的是，flatMap 并不能保证事件的顺序，如果需要保证，需要用到我们下面要讲的 ConcatMap。
     *
     * 是{{1,2,3},{4,5,6}{7,8,9}}，然后flatmap就认为是{1,2,3,4,5,6,7,8,9}
     * flatmap 多对一 flat 铺平
     */
    @Test
    public void flatMap() {
        List<Student> students = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        courses.add(Course.builder().className("语文").build());
        courses.add(Course.builder().className("数学").build());
        courses.add(Course.builder().className("英语").build());

        students.add(Student.builder().name("小明").course(courses).build());
        students.add(Student.builder().name("小王").course(new ArrayList<Course>() {{
            add(Course.builder().className("英语").build());
        }}).build());

        /**
         * 如果要打印出每个学生所需要修的所有课程的名称呢？（需求的区别在于，每个学生只有一个名字，但却有多个课程。）
         * */
        Observable.fromIterable(students).flatMap(new Function<Student, ObservableSource<Course>>() {
            @Override
            public ObservableSource<Course> apply(Student student) throws Exception {
                return Observable.fromIterable(student.getCourse());
            }
        }).subscribe(new Consumer<Course>() {
            @Override
            public void accept(Course course) throws Exception {
                System.out.println(course.className);
            }
        });
    }

    /**
     *  concatMap 与 FlatMap 的唯一区别就是 concatMap 保证了顺序
     * */
    @Test
    void concatMap() throws  InterruptedException {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        System.out.println("accept : " + s);
                    }
                });
        // 阻塞一下 等待线程完整输出
        Thread.sleep(2000);
    }

    /**
     * dispose 让Observer观察者不再接收上游事件
     */
    @Test
    void dispose() throws InterruptedException {

        Disposable disposable = Observable.interval(1, TimeUnit.SECONDS).doOnDispose(() -> {
            System.out.println("i am over");
        }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long item) throws Exception {
                System.out.println(item);
            }

        });
        TimeUnit.SECONDS.sleep(3);
        disposable.dispose();
    }


}

/**
 * 每个学生只有一个名字，但却有多个课程
 */
@Data
@Builder
class Student {
    String name;
    List<Course> course;

}

@Data
@Builder
class Course {
    String className;
}