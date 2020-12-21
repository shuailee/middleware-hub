package com.shuailee.proxydesignmode.dymicproxy.cglibdemo;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-05-06 22:23
 **/
public class TargetMethodCallbackFilter  implements CallbackFilter {
    //如果们想对类A的B方法使用一种拦截策略、类A的C方法使用另外一种拦截策略。则需要采用回调过滤器CallbackFilter
    //回调过滤器CallbackFilter: 在CGLib回调时可以设置对不同方法执行不同的回调逻辑，或者根本不执行回调。
    //在JDK动态代理中并没有类似的功能，对InvocationHandler接口方法的调用对代理类内的所以方法都有效。

    /**
     * 过滤方法
     * 返回的值为数字，代表了Callback数组中的索引位置，要使用到的Callback
     */
    @Override
    public int accept(Method method) {
        if(method.getName().equals("method1")){
            System.out.println("filter method1 ==0");
            return 0;
        }
        if(method.getName().equals("method2")){
            System.out.println("filter method2 ==1");
            return 1;
        }
        if(method.getName().equals("method3")){
            System.out.println("filter method3 ==2");
            return 2;
        }
        return 0;
    }

}
