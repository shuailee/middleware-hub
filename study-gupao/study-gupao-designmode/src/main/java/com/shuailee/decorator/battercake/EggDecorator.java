package com.shuailee.decorator.battercake;

/**
 * @program: study-gupao
 * @description: 具体装饰着
 * @author: shuai.li
 * @create: 2019-06-05 21:59
 **/
public class EggDecorator extends BattercakeDecotator {
    public EggDecorator(Battercake battercake) {
        super(battercake);
    }
    protected void doSomething() {}
    @Override
    protected String getMsg() {
        return super.getMsg() + "+1 个鸡蛋";
    }
    @Override
    protected int getPrice() {
        return super.getPrice() + 1;
    }
}
