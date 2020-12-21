package com.shuailee.proxydesignmode.dymicproxy.cglibdemo;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-05-06 22:38
 **/
public class TargetInterceptor2 implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //参数：Object为由CGLib动态生成的代理类实例，
        // Method为上文中实体类所调用的被代理的方法引用，
        // Object[]为参数值列表，
        // MethodProxy为生成的代理类对方法的代理引用。
        // 返回：从代理实例的方法调用返回的值。
        System.out.println("拦截器2：before");
        //调用代理类实例上的proxy方法的父类方法（即实体类TargetObject中对应的方法）
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println(" 拦截器2：after"+result);
        return result;

    }
}
