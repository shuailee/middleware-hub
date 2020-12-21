package com.shuailee.mock;

import com.shuailee.IHello;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-03-12 20:48
 **/
public class HelloTestMock implements IHello {
    @Override
    public String sayHello(String msg) {
        return "hello mock:"+msg;
    }
}
