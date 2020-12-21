package com.shuailee.strategy.paystrategy;

import java.math.BigDecimal;

public class Order {
    private String uid;
    private String orderid;
    private BigDecimal amount;

    public Order(String uid, String orderid, BigDecimal amount) {
        this.uid = uid;
        this.orderid = orderid;
        this.amount = amount;
    }

    /**
     * 默认支付
     * */
    public PayResult pay() {
       return pay(PayStrategy.DEFAULT_PAY);
    }
    /**
     * 指定支付方式支付
     * 完美的消除了switch 或if else
     * */
    public PayResult pay(String paykey){
        Payment payment=PayStrategy.get(paykey);
        System.out.println("你正在使用"+payment.getName());
        System.out.println("本次交易金额为"+amount+"，开始扣款...");
        return payment.pay(uid,amount);
    }
}
