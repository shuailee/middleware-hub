package com.shuailee.decorator.gundec;

/**
 * @program: study-gupao
 * @description: 被装饰类，是一个具有完整功能的具体类
 * @author: shuai.li
 * @create: 2019-06-05 20:59
 **/
public class Kar98K implements Gun {
    @Override
    public void fire() {
        System.out.println("砰*5");
    }
}
