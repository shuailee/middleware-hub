package com.shuailee.proxydesignmode.dymicproxy.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: study-gupao
 * @description: jdk动态代理类
 * @author: shuai.li
 * @create: 2019-04-25 18:20
 **/
public class DymaicProxyMedium implements InvocationHandler {
    /*
    * JDK自带的动态代理主要是指，实现了InvocationHandler接口的代理类，实现了InvocationHandler接口的类，会继承一个invoke方法，
    * 通过在这个方法中添加某些代码，从而完成在方法前后添加一些动态的东西。JDK自带的动态代理依赖于接口，如果有些类没有接口，则不能实现动态代理。
    * */

    //被代理的对象，把引用给保存下来
    private Object target;
    public Object getInstance(Object target) throws Exception{
        this.target = target;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(this.target,args);
        after();
        return obj;
    }



    private void after() {
        System.out.println("接触成功，资料归档");
    }

    private void before() {
        System.out.println("中介开始物色");
    }
}
