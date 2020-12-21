package com.shuailee.strategy.promotionstrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: study-gupao
 * @description: 策略工厂，结合单例模式和工厂模式  工厂本身是一个单例，客户端使用工厂获取对象
 * @author: shuai.li
 * @create: 2019-05-14 14:16
 **/
public class PromotionStrategyFactory {

    /**
     * 优惠策略容器
     * */
    private static Map<String,PromotionStrategy> PROMOTION_STRATEGY_MAP=new HashMap<>();
    static {
        PROMOTION_STRATEGY_MAP.put("COUPON",new CouponStrategy());
        PROMOTION_STRATEGY_MAP.put("CASHBACK",new CashbackStrategy());
        PROMOTION_STRATEGY_MAP.put("GROUPBUY",new GroupbuyStrategy());
    }

    private PromotionStrategyFactory(){}

    public static PromotionStrategy getPromotionStrategy(String promotionKey){
        PromotionStrategy promotionStrategy = PROMOTION_STRATEGY_MAP.get(promotionKey);
        return promotionStrategy == null ? new EmptyStrategy() : promotionStrategy;
    }
}
