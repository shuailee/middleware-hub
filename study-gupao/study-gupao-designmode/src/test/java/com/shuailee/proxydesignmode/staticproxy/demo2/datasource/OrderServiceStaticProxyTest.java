package com.shuailee.proxydesignmode.staticproxy.demo2.datasource;

import com.shuailee.proxydesignmode.staticproxy.demo2.IOrderService;
import com.shuailee.proxydesignmode.staticproxy.demo2.OrderPojo;
import com.shuailee.proxydesignmode.staticproxy.demo2.OrderServiceImpl;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderServiceStaticProxyTest {

    @Test
    public void createorder()
    {
        OrderPojo order = new OrderPojo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
            date = sdf.parse("2017/02/01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setCreateTime(date.getTime());


        IOrderService orderService = new OrderServiceStaticProxy(new OrderServiceImpl());
        orderService.createOrder(order);
    }

}