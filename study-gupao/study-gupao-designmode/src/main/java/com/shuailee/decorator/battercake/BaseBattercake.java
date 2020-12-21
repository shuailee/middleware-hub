package com.shuailee.decorator.battercake;

/**
 * @program: study-gupao
 * @description: 煎饼具体实现类- 被装饰对象
 * @author: shuai.li
 * @create: 2019-06-05 21:56
 **/
public class BaseBattercake extends  Battercake{
    @Override
    protected String getMsg() {
        return "煎饼";
    }

    @Override
    protected int getPrice() {
        return 5;
    }
}
