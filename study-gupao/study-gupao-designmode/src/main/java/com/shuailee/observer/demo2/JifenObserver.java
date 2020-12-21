package com.shuailee.observer.demo2;

public class JifenObserver implements IObesever {
    @Override
    public void update(String msg) {
        System.out.println("积分服务收到消息" + msg);
    }
}
