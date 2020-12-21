package com.shuailee.singleton;

/**
 * @program: study-gupao
 * @description: 懒汉模式实现方式2
 * @author: shuai.li
 * @create: 2018-10-26 14:07
 **/
public class LazySingletonTwo {

    /**
     //懒汉式单例
     //内部类特点：在外部类被调用的时候内部类才会被加载
     //内部类一定是要在方法调用之前初始化
     //巧妙地避免了线程安全问题

     //这种形式兼顾饿汉式的内存浪费，也兼顾synchronized性能问题
     //完美地屏蔽了这两个缺点
     //史上最牛B的单例模式的实现方式
     *
     * */

    private LazySingletonTwo(){
        if(LazyHolder.LAZY != null){
            //避免反射破坏单例
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    /**
     * 每一个关键字都不是多余的
     * static 是为了使单例的空间共享
     * final保证这个方法不会被重写，重载
     * */
    public static final LazySingletonTwo getInstance(){
        //在返回结果以前，一定会先加载内部类
        return LazyHolder.LAZY;
    }

    //默认不加载
    private static class LazyHolder{
        private static final LazySingletonTwo LAZY = new LazySingletonTwo();
    }

}
