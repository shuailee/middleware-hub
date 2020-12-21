package com.shuailee.proxydesignmode.staticproxy.demo1;

/**
 * @program: study-gupao
 * @description: 媒介类，找对象的中介，持有被代理对象的引用
 * @author: shuai.li
 * @create: 2019-04-25 15:53
 **/
public class Medium implements Person {

    /**
     * 是指为其他对象提供一种代理，以控制对这个对象的访问
     * 代理对象在客服端和目标对象之间起到中介作用
     * 使用代理模式主要有两个目的：一保护目标对象，二增强目标对象(在被代理对象执行前后加入行为aop)
     *
     * */

    private Customer target;
    public Medium(Customer target)
    {
        this.target=target;
    }

    @Override
    public void findObject() {
        /**
         * 增强目标对象(在被代理对象执行前后加入行为aop)
         * */
        before();
        this.target.findObject();
        after();
    }



    private void before() {
        System.out.println("客户资质审核");
    }
    private void after() {
        System.out.println("客户找到对象，资料存档");
    }

}
