package com.shuailee.observer.demo2;

public class EmailObserver implements IObesever {
    @Override
    public void update(String msg) {
        System.out.println("邮件服务："+msg);
    }
}
