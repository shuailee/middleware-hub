package com.shuailee.strategy.paystrategy;

import java.math.BigDecimal;

/**
 * 支付抽象类
 * */
public abstract class Payment {
    /**
     * 支付類型
     * */
    public abstract  String getName();
    /**
     * 餘額查詢
     * */
    public abstract BigDecimal queryBalance(String uid);

    /**
     * 扣款支付
     * */
    public PayResult pay(String uid,BigDecimal amount)
    {
        if(queryBalance(uid).compareTo(amount)<0){
            return new PayResult("500","余额不足","支付失败");
        }else {
            return new PayResult("200","支付成功","支付金额"+amount);
        }

    }
}
