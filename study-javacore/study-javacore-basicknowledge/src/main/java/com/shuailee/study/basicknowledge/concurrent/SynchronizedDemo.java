package com.shuailee.study.basicknowledge.concurrent;

public class SynchronizedDemo {
    public  static  void main(String [] args){
        Example em=new Example();
        Thread t1=new MyThread(em);
        Thread t2=new MyThread(em);
        t1.start();
        t2.start();
    }

}

class Example {
    //被synchronized保护的数据应该是私有的
    private Object obj = new Object();
    public void method1() {
        /*
        * synchronized块比synchronized方法更加细粒度地控制了多个线程的访问，只有synchronized块中的内容不能同时被多个线程所访问，
        * 方法中的其他语句仍然可以同时被多个线程所访问（包括synchronized块之前的和之后的）
        * synchronized (obj) 表示线程在执行的时候会将object对象上锁。（注意这个对象可以是任意类的对象，也可以使用this关键字）。
        * */
        synchronized (obj) {
            for (int i = 0; i < 20; ++i) {
                try {
                    Thread.sleep((long) Math.random() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("World: " + i);
            }
        }
    }

}

//线程对象
class MyThread extends Thread{
    private Example example;
    public  MyThread(Example example){
        this.example=example;
    }
    @Override
    public void run() {
        example.method1();
    }
}
