package com.shuailee.observer.demo2;

public class AppMain {
    public static void main(String[] args) {
        RegisterSubject registerSubject=new RegisterSubject("919@qq.com");
        registerSubject.attach(new JifenObserver());
        registerSubject.attach(new EmailObserver());
        registerSubject.notifyObesever();
    }
}
