package com.shuailee.proxydesignmode.dymicproxy.cglibdemo;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: study-gupao
 * @description: 定义一个拦截器。在调用目标方法时，CGLib会回调MethodInterceptor接口方法拦截，
 * 来实现你自己的代理逻辑，类似于JDK中的InvocationHandler接口。
 * @author: shuai.li
 * @create: 2019-05-06 21:39
 **/
public class TargetInterceptor implements MethodInterceptor {
    /**
     * 重写方法拦截在方法前和方法后加入业务
     * Object obj为目标对象
     * Method method为目标方法
     * Object[] params 为参数，
     * MethodProxy proxy CGlib方法代理对象
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //参数：Object为由CGLib动态生成的代理类实例，
        // Method为上文中实体类所调用的被代理的方法引用，
        // Object[]为参数值列表，
        // MethodProxy为生成的代理类对方法的代理引用。
        // 返回：从代理实例的方法调用返回的值。
        System.out.println("调用前");
        //调用代理类实例上的proxy方法的父类方法（即实体类TargetObject中对应的方法）
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println(" 调用后"+result);
        return result;

        //使用了该种过滤器的所有代理方法调用都会加入相同的拦截，如果们想对类A的B方法使用一种拦截策略、类A的C方法使用另外一种拦截策略。则需要采用回调过滤器CallbackFilter
    }
}
