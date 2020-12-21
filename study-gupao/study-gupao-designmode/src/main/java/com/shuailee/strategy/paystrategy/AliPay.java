package com.shuailee.strategy.paystrategy;

import java.math.BigDecimal;
/**
 * 实际支付类
 * */
public class AliPay extends Payment {
    @Override
    public String getName() {
        return "支付宝付款";
    }
    @Override
    public BigDecimal queryBalance(String uid) {
        return BigDecimal.valueOf(320);
    }
}
