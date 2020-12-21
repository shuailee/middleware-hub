package com.shuailee.observer.demo1;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-05 23:02
 **/
public class Test {
    public static void main(String[] args) {
        //被观察者 wechat公众号
        WechatServer  ws=new WechatServer();

        //观察者
        Observer mic=new User("Mic");
        Observer tom=new User("Tom");
        ws.registerObserver(mic);
        ws.registerObserver(tom);
        //发布消息
        ws.setInfomation("hello world");
        //通知订阅者
        ws.notifyObserver();
    }
}
