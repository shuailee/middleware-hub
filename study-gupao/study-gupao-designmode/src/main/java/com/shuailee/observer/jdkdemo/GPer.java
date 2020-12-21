package com.shuailee.observer.jdkdemo;

import java.util.Observable;

/**
 * @program: study-gupao
 * @description:  JDK 提供的一种观察者的实现方式，Observable 被观察者（任务发布者）
 * @author: shuai.li
 * @create: 2019-06-05 22:28
 **/
public class GPer extends Observable {
    private String name = "GPer 生态圈";
    private static GPer gper = null;
    private GPer(){}
    public static GPer getInstance(){
        if(null == gper){
            gper = new GPer();
        }
        return gper;
    }

    public String getName() {
        return name;
    }
    public void publishQuestion(Question question){
        System.out.println(question.getUserName() + "在" + this.name + "上提交了一个问题。");
        setChanged();
        notifyObservers(question);
    }

}
