package com.shuailee.strategy.promotionstrategy;

/**
 * @program: study-gupao
 * @description: 拼团优惠策略
 * @author: shuai.li
 * @create: 2019-05-14 13:45
 **/
public class GroupbuyStrategy implements PromotionStrategy {


    @Override
    public void doPromotion() {
        System.out.println("拼团优惠");
    }
}
