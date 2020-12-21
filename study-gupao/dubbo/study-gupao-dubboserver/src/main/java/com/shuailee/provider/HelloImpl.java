package com.shuailee.provider;

import com.shuailee.IHello;

public class HelloImpl implements IHello {
    @Override
    public String sayHello(String msg) {
        return "hello:"+msg;
    }
}
