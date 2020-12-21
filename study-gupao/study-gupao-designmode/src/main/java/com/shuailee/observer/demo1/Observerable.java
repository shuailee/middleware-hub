package com.shuailee.observer.demo1;

/**
 * @program: study-gupao
 * @description: 抽象被观察者接口
 * 声明了添加、删除、通知观察者方法
 * @author: shuai.li
 * @create: 2019-06-05 22:49
 **/
public interface Observerable {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    /**
     * 当被观察者调用notifyObservers()方法时，观察者的回调方法会被回调
     * */
    public void notifyObserver();
}
