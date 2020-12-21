package com.shuailee.observer.jdkdemo;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-05 22:33
 **/
public class ObserverTest {
    public static void main(String[] args) {
        //事件主题，发布者（被观察者）
        GPer gper = GPer.getInstance();

        //添加观察者
        Teacher tom = new Teacher("Tom");
        Teacher mic = new Teacher("Mic");
        gper.addObserver(tom);
        gper.addObserver(mic);

        //业务逻辑代码
        Question question = new Question();
        question.setUserName("小明");
        question.setContent("观察者模式适用于哪些场景？");
        //发布者发布消息，订阅者都会受到消息
        gper.publishQuestion(question);
    }
}
