package com.shuailee.strategy.paystrategy;

import java.math.BigDecimal;


public class PatTest {
    /**
     * 一个常见的应用场景就是大家在下单
     * 支付时会提示选择支付方式，如果用户未选，系统也会默认好推荐的支付方式进行结算。
     * */
    public static void main(String[] args) {
        Order order=new Order("10000","20190518",BigDecimal.valueOf(100));
        System.out.println(order.pay(PayStrategy.ALI_PAY));
    }


}
