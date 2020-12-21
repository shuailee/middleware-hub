package com.shuailee.proxydesignmode.staticproxy.demo2.datasource;

import com.shuailee.proxydesignmode.staticproxy.demo2.IOrderService;
import com.shuailee.proxydesignmode.staticproxy.demo2.OrderPojo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: study-gupao
 * @description: 切换数据源的代理（扩展数据源）
 * @author: shuai.li
 * @create: 2019-04-25 16:37
 **/
public class OrderServiceStaticProxy implements IOrderService {
    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private IOrderService orderService;
    public OrderServiceStaticProxy(IOrderService orderService){
        this.orderService = orderService;
    }


    @Override
    public int createOrder(OrderPojo order) {
        before();

        //根据订单创建时间自动按年进行分库。根据开闭原则，原来写好的逻辑我们不去修改，通过代理对象来完成
        Long time = order.getCreateTime();
        Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
        System.out.println("静态代理类自动分配到【DB_" + dbRouter + "】数据源处理数据。");
        //设置新的数据源到路由对象
        DynamicDataSourceEntry.set(dbRouter);

        //在目标对象的方法执行前设置动态数据源
        orderService.createOrder(order);

        after();
        return 0;
    }



    private void before() {
        System.out.println("Proxy before method.");
    }

    private void after() {
        System.out.println("Proxy after method.");
    }
}
