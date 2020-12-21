package com.shuailee.singleton;

/**
 * @program: study-gupao
 * @description: 饿汉式单例
 * @author: shuai.li
 * @create: 2018-10-26 13:52
 **/
public class Hungry {
    /**
     * 保证一个类只有一个实例，并提供一个它的全局访问点
     * 它是在类加载的时候就立即初始化，并且创建单例对象。绝对线程安全，在线程还没出现以前就是实例化了，不可能存在访问安全问题
     * 静态的，类加载的时候就被初始化了
     * */
    private Hungry(){}
    private static final Hungry hungry=new Hungry();
    public static Hungry getInstance(){
        return hungry;
    }

}
