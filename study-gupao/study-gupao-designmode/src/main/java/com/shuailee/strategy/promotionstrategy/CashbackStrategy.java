package com.shuailee.strategy.promotionstrategy;

/**
 * @program: study-gupao
 * @description: 返现促销策略
 * @author: shuai.li
 * @create: 2019-05-14 13:45
 **/
public class CashbackStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("返现促销");
    }
}
