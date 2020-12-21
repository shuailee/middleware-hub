package com.shuailee.proxydesignmode.dymicproxy.demo1;

import com.shuailee.proxydesignmode.staticproxy.demo1.Person;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-04-25 15:52
 **/
public class DymaicCustomer implements Person {
    @Override
    public void findObject() {
        System.out.println("客户找对象：高富帅");
    }
}
