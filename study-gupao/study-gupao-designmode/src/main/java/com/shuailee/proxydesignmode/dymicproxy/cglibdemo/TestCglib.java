package com.shuailee.proxydesignmode.dymicproxy.cglibdemo;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * @program: study-gupao
 * @description: 生成动态代理类
 * @author: shuai.li
 * @create: 2019-05-06 21:52
 **/
public class TestCglib {

    public static void main(String[] args) {
        //targetInterceptorTest();
        targetMethodCallbackFilterTest();
    }

    /**
     * 使用Cglib定义不同的拦截策略
     * */
    private static void targetMethodCallbackFilterTest() {
        //Enhancer类是CGLib中的一个字节码增强器，它可以方便的对你想要处理的类进行扩展
        Enhancer enhancer =new Enhancer();
        //首先将被代理类TargetObject设置成父类
        enhancer.setSuperclass(TargetObject.class);


        //NoOp.INSTANCE：这个NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
        Callback noopCb= NoOp.INSTANCE;
        //callback1：方法拦截器
        Callback callback1=new TargetInterceptor();
        Callback callback2=new TargetInterceptor2();
        //第一个使用方法拦截器TargetInterceptor（前后加增强操作）
        //第二个使用默认，什么也不做
        //第三个使用拦截器2TargetInterceptor2 （前后加增强操作）
        Callback[] cbarray=new Callback[]{callback1,noopCb,callback2};
        enhancer.setCallbacks(cbarray);

        CallbackFilter callbackFilter = new TargetMethodCallbackFilter();
        enhancer.setCallbackFilter(callbackFilter);
        TargetObject targetObject2=(TargetObject)enhancer.create();

        System.out.println(targetObject2.method1("mmm1"));
        System.out.println("------------------------------------");
        System.out.println(targetObject2.method2(100));
        System.out.println("------------------------------------");
        System.out.println(targetObject2.method3(200));
        System.out.println("------------------------------------");
    }

    /**
     * 方法拦截器测试
     * */
    private static void targetInterceptorTest() {
        //Enhancer类是CGLib中的一个字节码增强器，它可以方便的对你想要处理的类进行扩展
        Enhancer enhancer =new Enhancer();
        //首先将被代理类TargetObject设置成父类
        enhancer.setSuperclass(TargetObject.class);
        //然后设置拦截器TargetInterceptor
        enhancer.setCallback(new TargetInterceptor());
        //最后执行enhancer.create()动态生成一个代理类,并从Object强制转型成父类型TargetObject。
        TargetObject targetObject2=(TargetObject)enhancer.create();
        //在代理类上调用方法:targetObject2.method1("mmm1")
        System.out.println("返回值："+targetObject2.method1("mmm1"));
    }


}
