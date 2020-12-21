package com.shuailee.observer.demo1;

/**
 * @program: study-gupao
 * @description: 抽象观察者接口
 * 定义了一个update()方法，当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调。
 * @author: shuai.li
 * @create: 2019-06-05 22:53
 **/
public interface Observer {
    public void update(String message);
}
