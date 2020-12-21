package com.shuailee.study.basicknowledge.concurrent;



import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JoinDemo {

    public static void main(String[] args) {
        try {
            ThreadA t1 = new ThreadA("t1");

            t1.start();                      // 启动“线程t1”
            t1.join();                        // 将“线程t1”加入到“主线程main”中，并且“主线程main()会等待它的完成”,如果没有join则主函数会先执行完毕
            System.out.printf("%s 结束\n", Thread.currentThread().getName());
        } catch (Exception e) { //InterruptedException
            e.printStackTrace();
        }
    }

    //子线程
    static class ThreadA extends Thread {
        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            System.out.printf("%s start\n", this.getName());
            // 延时操作
            for (int i = 0; i < 10000; i++) {
                //System.out.println(i);
            }

            System.out.printf("%s 结束\n", this.getName());
        }
    }
}

class JoinAppDemo {

    static List<String> order = new ArrayList<>();
    static List<String> order2 = new ArrayList<>();

    public static void main(String[] args) {

        try {
            JoinAppDemo joinAppDemo = new JoinAppDemo();
            //开启线程处理两个各自不相互联系的任务
            Thread t1 = new Thread(() -> {
                order.addAll(joinAppDemo.getOrderByMSsqldb());
            });
            t1.start();
            Thread t2 = new Thread(() -> {
                order2.addAll(joinAppDemo.getOrderByMysqldb());
            });
            t2.start();
            //等待两个线程都执行完毕
            t2.join();
            t1.join();

            //对两个任务返回的结果处理
            order.addAll(order2);
            System.out.println(StringUtils.join(order,"-"));


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public List<String> getOrderByMSsqldb() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Arrays.asList("1", "2", "3");
    }

    public List<String> getOrderByMysqldb() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList("4", "5", "6");
    }


}
