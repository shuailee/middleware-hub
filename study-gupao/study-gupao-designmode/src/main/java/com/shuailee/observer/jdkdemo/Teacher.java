package com.shuailee.observer.jdkdemo;

import java.util.Observable;
import java.util.Observer;

/**
 * @program: study-gupao
 * @description: Observer观察者（任务订阅者）
 * @author: shuai.li
 * @create: 2019-06-05 22:28
 **/
public class Teacher implements Observer {
    private String name;
    public Teacher(String name){
        this.name = name;
    }
    @Override
    public void update(Observable o, Object arg) {
        GPer gper = (GPer)o;
        Question question = (Question)arg;
        System.out.println("===============================");
        System.out.println(name + "老师，你好！\n" +
                "您收到了一个来自“" + gper.getName() + "”的提问，希望您解答，问题内容如下：\n" +
                question.getContent() + "\n" +"提问者：" + question.getUserName());
    }
}
