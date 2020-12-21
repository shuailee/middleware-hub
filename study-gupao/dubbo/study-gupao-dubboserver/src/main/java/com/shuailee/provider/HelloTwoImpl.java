package com.shuailee.provider;

import com.shuailee.IHello;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-03-12 13:39
 **/
public class HelloTwoImpl implements IHello {
    @Override
    public String sayHello(String msg) {
        return "hello 2:"+msg;
    }
}