package com.shuailee.decorator.battercake;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-05 22:00
 **/
public class SausageDecorator extends BattercakeDecotator {
    public SausageDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+1 根香肠";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 2;
    }
}
