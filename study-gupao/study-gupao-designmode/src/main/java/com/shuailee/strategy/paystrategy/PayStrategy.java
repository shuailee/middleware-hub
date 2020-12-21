package com.shuailee.strategy.paystrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付策略类
 * */
public class PayStrategy {
    public static final String ALI_PAY="alipay";
    public static final String WECHAT_PAY="wechatpay";
    public static final String DEFAULT_PAY="alipay";

    /**
     * 静态模块单例
     * */
    public static Map<String,Payment> paymentMap=new HashMap<>();
    static {
        paymentMap.put(ALI_PAY,new AliPay());
        paymentMap.put(WECHAT_PAY,new WechatPay());
        paymentMap.put(DEFAULT_PAY,new AliPay());
    }

    public static Payment get(String pay){
        if(!paymentMap.containsKey(pay)){
            return paymentMap.get(DEFAULT_PAY);
        }else {
            return paymentMap.get(pay);
        }
    }

}
