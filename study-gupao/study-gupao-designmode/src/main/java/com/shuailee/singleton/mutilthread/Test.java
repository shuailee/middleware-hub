package com.shuailee.singleton.mutilthread;

import com.shuailee.singleton.LazySingleton;

public class Test {
    public static void main(String[] args) {
        ExectorThread exectorThread=new ExectorThread();

        Thread t1=new Thread(exectorThread,"Thread-1");
        Thread t2=new Thread(exectorThread,"Thread-2");
        t1.start();
        t2.start();
        System.out.println("end");

        //
        new Thread(){
            public void Run(){
                LazySingleton singleton=LazySingleton.getInstance();
                System.out.println(Thread.currentThread().getName()+":"+singleton);
            }
        }.start();
    }
}
