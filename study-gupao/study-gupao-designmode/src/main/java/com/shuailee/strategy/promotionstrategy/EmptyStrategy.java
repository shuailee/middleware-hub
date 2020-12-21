package com.shuailee.strategy.promotionstrategy;

/**
 * @program: study-gupao
 * @description:无优惠
 * @author: shuai.li
 * @create: 2019-05-14 14:00
 **/
public class EmptyStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("无优惠");
    }
}
