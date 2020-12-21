package com.shuailee.observer.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: study-gupao
 * @description: 被观察者，也就是微信公众号服务，维护一个观察者的集合以便通知观察者
 * 实现了Observerable接口，对Observerable接口的三个方法进行了具体实现
 * @author: shuai.li
 * @create: 2019-06-05 22:56
 **/
public class WechatServer implements Observerable {

    //注意到这个List集合的泛型参数为Observer接口
    // 设计原则：面向接口编程而不是面向实现编程
    private List<Observer> list;
    private String message;


    public WechatServer() {
        list = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if(!list.isEmpty()){
            list.remove(o);
        }
    }

    @Override
    public void notifyObserver() {
        //循环通知所有观察者，通过调用观察者的update方法
        for(int i = 0; i < list.size(); i++) {
            Observer oserver = list.get(i);
            oserver.update(message);
        }
    }


    public void setInfomation(String s) {
        this.message = s;
        System.out.println("微信服务发布消息： " + s);
        //消息更新，通知所有观察者
        notifyObserver();
    }
}
