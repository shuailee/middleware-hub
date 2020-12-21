package com.shuailee.study.basicknowledge.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: study-javacore
 * @description:
 * @author: shuai.li
 * @create: 2018-11-13 10:56
 **/
public class FeaturetaskDemo {

    /**
     * 通过使用线程池来执行futuretask
     * */
    public static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);


    /**

            1 当你使用多线程时，比如你想用子线程执行一个耗时任务【比如说下载一个大文件】，在这个任务执行完之前，
        你还想接着干其他的事情【主线程响应其它事件】，然后当你需要这个子线程执行的结果时，拿到它。
            2 创建线程的方式有两种：一种是实现Runnable接口，另一种是继承Thread。但这两种方式都无法获取执行后的结果。但是通过Callable接口和Future接口，
        我们可以拿到执行后的结果。Callable有返回值，我们可以通过FutureTask.get()来取得Callable返回的值。
            3 Future只是一个接口，不能直接用来创建对象，FutureTask是Future的实现类，这个工具类有两个构造函数，它们的参数和前面介绍的 submit方法类似
            4  FutureTask类实际上是同时实现了Runnable和Future接口，由此才使得其具有Future和Runnable双重特性。通过Runnable特性，
        可以作为Thread对象的target，而Future特性，使得其可以取得新创建线程中的call()方法的返回值。
            5 Java 通过 ThreadPoolExecutor 提供的submit()方法和 1 个 FutureTask 工具类来支持获得任务执行结果的需求，它们的返回值都是 Future 接口,
        他们的参数是runable callable
            6 线程池的submit和execute方法区别:
                1) submit 有返回值，可以获取线程的执行结果.execute没有返回值
                2）submit方便Exception处理，如果你在你的task里会抛出checked或者unchecked exception，而你又希望外面的调用者能够感知
     这些exception并做出及时的处理，那么就需要用到submit，通过捕获Future.get抛出的异常。

    * */

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        new FeaturetaskDemo().simpleDemo();

       // new FeaturetaskDemo().mulitDemo();

    }


    /**
     * 主线程等待子任务执行完毕获得结果，再结束
     */
    public void simpleDemo() throws ExecutionException, InterruptedException {


        //Callable是函数式接口，所以这里可以用lambda表达式
        FutureTask<Integer> task = new FutureTask<Integer>(()->{
            int sum = 0;
            try {
                for (int i = 0; i < 5; i++) {
                    sum += i;
                    Thread.sleep(1000);
                    System.out.println(sum);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Integer.valueOf(sum);
        });
        fixedThreadPool.submit(task);

        System.out.println("runing");

        System.out.println("task.get()----->此处会阻塞直到子任务执行完毕" + task.get());

        System.out.println("end");

    }

    /**
     * 主线程等待多个子任务并行执行完毕并获得结果，
     * 计算各个子线程的计算结果后再结束
     */
    public void mulitDemo() {
        // 创建任务集合
        List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();

        for (int i = 0; i < 3; i++) {
            FutureTask<Integer> ft = new FutureTask<Integer>(new ComputeTask(""+i+""));
            taskList.add(ft);
            fixedThreadPool.submit(ft);
        }

        System.out.println("所有计算任务提交完毕, 主线程接着干其他事情！");

        // 开始统计各计算线程计算结果
        Integer totalResult = 0;
        for (FutureTask<Integer> ft : taskList) {
            try {
                System.out.println("task.get()----->" + ft.get());
                //FutureTask的get方法会自动阻塞,直到获取计算结果为止
                totalResult = totalResult + ft.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        // 关闭线程池
        fixedThreadPool.shutdown();
        System.out.println("多任务计算后的总结果是:" + totalResult);


    }

    /**
     * desc:
     * @parameter:
     * @return:
     */
    class ComputeTask implements Callable<Integer> {

        int sum = 0;
        String name;
        public ComputeTask(String name){
            this.name=name;
        }
        @Override
        public Integer call() throws Exception {
            for (int i = 0; i < 5; i++) {
                sum += i;
            }
            // 休眠5秒钟，观察主线程行为，预期的结果是主线程会继续执行，到要取得FutureTask的结果是等待直至完成。
            Thread.sleep(5000);
            System.out.println("子线程计算任务:"+name+" 执行完成!");

            return Integer.valueOf(sum);
        }
    }
}

