package com.shuailee.proxydesignmode.dymicproxy.cglibdemo;

/**
 * @program: study-gupao
 * @description: 没有实现接口，需要CGlib动态代理的目标类
 * @author: shuai.li
 * @create: 2019-05-06 21:37
 **/
public class TargetObject {
    public String method1(String paramName) {
        return paramName;
    }

    public int method2(int count) {
        return count;
    }

    public int method3(int count) {
        return count;
    }

    @Override
    public String toString() {
        return "TargetObject []"+ getClass();
    }
}
