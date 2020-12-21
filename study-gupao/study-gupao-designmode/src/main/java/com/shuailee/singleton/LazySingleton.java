package com.shuailee.singleton;

/**
 * @program: study-gupao
 * @description: 懒汉模式实现方式1
 * @author: shuai.li
 * @create: 2018-10-26 13:58
 **/
public class LazySingleton {


    /*private LazySingleton() {}
    private static LazySingleton lazySingleton = null;
    *//**
     * desc: 为了解决并发问题所以加锁，但是影响性能
     * @parameter: []  synchronized
     * @return: com.shuailee.singleton.LazySingleton
     *//*
    public static synchronized LazySingleton getInstance() {

        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        //如果產生并發問題，此處會有多個對象（單例模式是不允許多個對象出現的）
        System.out.println("對象："+lazySingleton.hashCode());
        return lazySingleton;
    }*/

    //DOUBLE CHECK
    private volatile static LazySingleton lazy = null;
    private LazySingleton(){}
    public static LazySingleton getInstance(){
        if(lazy == null){
            synchronized (LazySingleton.class){
                if(lazy == null){
                    //1.分配内存给这个对象
                    //2.初始化对象
                    //3.设置lazy 指向刚分配的内存地址
                    lazy = new LazySingleton();
                }
            }
        }
        return lazy;
    }

}
