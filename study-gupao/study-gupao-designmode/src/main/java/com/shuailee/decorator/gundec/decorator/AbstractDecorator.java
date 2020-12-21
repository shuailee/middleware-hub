package com.shuailee.decorator.gundec.decorator;

import com.shuailee.decorator.gundec.Gun;

/**
 * @program: study-gupao
 * @description: 装饰接口
 * 抽象装饰类也要实现和被装饰类相同的接口并持有对被装饰类的引用
 * @author: shuai.li
 * @create: 2019-06-05 21:09
 **/
public class AbstractDecorator implements Gun {

    private Gun gun;
    public AbstractDecorator(Gun gun){
        this.gun=gun;
    }
    @Override
    public void fire() {
        gun.fire();
    }
}
