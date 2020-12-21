package com.shuailee.proxydesignmode.staticproxy.demo1;

import org.junit.Test;

import static org.junit.Assert.*;

public class DymaicProxyMediumTest {

    /**
     * 静态代理测试类
     * */
    @Test
    public void findObjectTest() {
        Medium medium=new Medium(new Customer());
        medium.findObject();
    }

}