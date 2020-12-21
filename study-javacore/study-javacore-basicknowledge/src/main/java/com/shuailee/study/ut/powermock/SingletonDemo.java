package com.shuailee.study.ut.powermock;

/**
 * @program: study-javacore
 * @description:
 * @create: 2019-08-06 21:28
 **/
public class SingletonDemo {

    private static final SingletonDemo INSTANCE = new SingletonDemo();
    private SingletonDemo(){}

    public static SingletonDemo getInstance() {
        return INSTANCE;
    }

    /**
     * 非静态方法
     * */
    public Boolean getCustomer(String uid){
        System.out.println("query customer"+uid);
        return true;
    }

    /**
     * 静态方法
     * */
    public static Boolean checkVIP(String uid){
        System.out.println("query customer"+uid);
        return true;
    }

}
