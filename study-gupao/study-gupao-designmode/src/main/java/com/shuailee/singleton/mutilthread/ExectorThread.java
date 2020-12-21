package com.shuailee.singleton.mutilthread;

import com.shuailee.singleton.LazySingleton;

public class ExectorThread implements Runnable {
    @Override
    public void run() {
        LazySingleton singleton=LazySingleton.getInstance();
        System.out.println(Thread.currentThread().getName()+":"+singleton);
    }
}
