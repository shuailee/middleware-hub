package com.shuailee.strategy.promotionstrategy;

/**
 * @program: study-gupao
 * @description: 测试类
 * @author: shuai.li
 * @create: 2019-05-14 14:02
 **/
public class PromotionTest {
    public static void main(String[] args) {

        test1();
        test2();

    }

    public static void test1()
    {
        /*        //618返现促销
        PromotionActivity activity618=new PromotionActivity(new CashbackStrategy());
        //双11优惠券促销
        PromotionActivity activity1111=new PromotionActivity(new CouponStrategy());
        activity618.excute();
        activity1111.excute();*/

        //我们做活动时候往往是要根据不同的需求对促销策略进行动态选择的，并不会一次性执行多种优惠
        //这么写的弊端在于当促销活动种类越来越多，这里得增加很多判断，同时每增加一种活动类别这里都要做重复测试
        PromotionActivity promotionActivity = null;
        String promotionKey = "COUPON";
        if("COUPON".equals(promotionKey)){
            promotionActivity = new PromotionActivity(new CouponStrategy());
        }else if("CASHBACK".equals(promotionKey)){
            promotionActivity = new PromotionActivity(new CashbackStrategy());
        }
        promotionActivity.excute();
    }

    public static void test2() {

        //每次上新活动，不影响原来的代码逻辑

        //要进行的活动
        String promotionKey = "GROUPBUY";
        //使用策略工厂获取对应具体的活动策略
        PromotionStrategy promotionStrategy=PromotionStrategyFactory.getPromotionStrategy(promotionKey);
        PromotionActivity promotionActivity=new PromotionActivity(promotionStrategy);
        //执行优惠策略
        promotionActivity.excute();
    }

}
