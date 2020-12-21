package com.shuailee.decorator.battercake;

/**
 * @program: study-gupao
 * @description: 扩展套餐的抽象装饰者BattercakeDecotator 类
 * @author: shuai.li
 * @create: 2019-06-05 21:56
 **/
public abstract class BattercakeDecotator extends Battercake{
    //静态代理，委派
    private Battercake battercake;
    public BattercakeDecotator(Battercake battercake) {
        this.battercake = battercake;
    }

    @Override
    protected String getMsg() {
        return this.battercake.getMsg();
    }
    @Override
    protected int getPrice() {
        return this.battercake.getPrice();
    }
}
