package com.shuailee.proxydesignmode.staticproxy.demo1;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-04-25 15:52
 **/
public class Customer implements Person {
    @Override
    public void findObject() {
        System.out.println("客户找对象：白富美");
    }
}
