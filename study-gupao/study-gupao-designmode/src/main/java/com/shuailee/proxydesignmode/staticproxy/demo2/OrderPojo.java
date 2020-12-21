package com.shuailee.proxydesignmode.staticproxy.demo2;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-04-25 16:19
 **/
public class OrderPojo {
    private Object orderInfo;
    private Long createTime;
    private String id;
    public Object getOrderInfo() {
        return orderInfo;
    }
    public void setOrderInfo(Object orderInfo) {
        this.orderInfo = orderInfo;
    }
    public Long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
