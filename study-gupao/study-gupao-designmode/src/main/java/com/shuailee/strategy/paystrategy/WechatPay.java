package com.shuailee.strategy.paystrategy;

import java.math.BigDecimal;

public class WechatPay extends Payment{
    @Override
    public String getName() {
        return "微信支付";
    }
    @Override
    public BigDecimal queryBalance(String uid) {
        return BigDecimal.valueOf(120);
    }
}
