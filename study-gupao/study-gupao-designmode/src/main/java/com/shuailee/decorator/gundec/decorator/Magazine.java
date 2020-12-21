package com.shuailee.decorator.gundec.decorator;

import com.shuailee.decorator.gundec.Gun;

/**
 * @program: study-gupao
 * @description: 弹夹装饰实现
 * @author: shuai.li
 * @create: 2019-06-05 21:14
 **/
public class Magazine extends AbstractDecorator {
    public Magazine(Gun gun) {
        super(gun);
    }
    @Override
    public void fire() {
        System.out.println("砰*10");
    }
}
