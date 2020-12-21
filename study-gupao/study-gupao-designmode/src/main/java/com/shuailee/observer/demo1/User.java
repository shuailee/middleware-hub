package com.shuailee.observer.demo1;

/**
 * @program: study-gupao
 * @description: 具体观察者，实现了update方法
 * @author: shuai.li
 * @create: 2019-06-05 23:00
 **/
public class User implements  Observer{
    private String name;
    private String message;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    public void read() {
        System.out.println(name + " 收到推送消息： " + message);
    }
}
