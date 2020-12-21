package com.shuailee.decorator.battercake;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-05 22:02
 **/
public class BattercakeTest {
    public static void main(String[] args) {
        //买个煎饼
        Battercake battercake=new BaseBattercake();
        System.out.println(battercake.getMsg());
        System.out.println(battercake.getPrice());
        //加个蛋
        battercake=new EggDecorator(battercake);
        System.out.println(battercake.getMsg());
        System.out.println(battercake.getPrice());

        //跟静态代理最大区别就是职责不同
        //静态代理不一定要满足is-a 的关系
        //静态代理会做功能增强，同一个职责变得不一样,例如在做某事情之前，之后要加日志
        //装饰器更多考虑是扩展，例如做了某事情再这个事情基础上在做些别的事情；
        System.out.println(battercake.getMsg() + ",总价：" + battercake.getPrice());
    }
}
