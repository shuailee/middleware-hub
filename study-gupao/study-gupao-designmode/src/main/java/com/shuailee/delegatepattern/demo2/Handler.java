package com.shuailee.delegatepattern.demo2;

import java.lang.reflect.Method;

/**
 * @program: study-gupao
 * @description: 支持链式调用的handler
 * @author: shuai.li
 * @create: 2019-05-13 19:25
 **/
public class Handler {
    /**
     * controller对象
     * */
    private Object controller;
    /**
     * controller对象映射的方法
     * */
    private String url;
    /**
     * ulr对应的方法
     * */
    private Method method;

    public Object getController() {
        return controller;
    }

    public Handler setController(Object controller) {
        this.controller = controller;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Handler setUrl(String url) {
        this.url = url;
        return this;
    }

    public Method getMethod() {
        return method;
    }

    public Handler setMethod(Method method) {
        this.method = method;
        return this;
    }
}
