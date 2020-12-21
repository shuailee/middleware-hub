package com.shuailee.strategy.paystrategy;
/**
 * 支付状态类
 * */
public class PayResult {
    private String code;
    private String msg;
    private Object data;

    public PayResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "PayResult{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
