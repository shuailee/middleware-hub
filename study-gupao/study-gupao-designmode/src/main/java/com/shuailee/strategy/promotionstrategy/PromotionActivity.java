package com.shuailee.strategy.promotionstrategy;

/**
 * @program: study-gupao
 * @description: 促销活动方案 执行类
 * @author: shuai.li
 * @create: 2019-05-14 14:00
 **/
public class PromotionActivity {
    private PromotionStrategy promotionStrategy;
    public PromotionActivity (PromotionStrategy promotionStrategy){
        this.promotionStrategy = promotionStrategy;
    }
    public void excute()
    {
        promotionStrategy.doPromotion();
    }
}
