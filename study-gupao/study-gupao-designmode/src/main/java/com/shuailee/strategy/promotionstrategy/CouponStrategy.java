package com.shuailee.strategy.promotionstrategy;

/**
 * @program: study-gupao
 * @description: 优惠券促销
 * @author: shuai.li
 * @create: 2019-05-14 13:41
 **/
public class CouponStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("优惠券促销");
    }
}
