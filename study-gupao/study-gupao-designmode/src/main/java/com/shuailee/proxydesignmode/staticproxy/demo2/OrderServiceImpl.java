package com.shuailee.proxydesignmode.staticproxy.demo2;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-04-25 16:25
 **/
public class OrderServiceImpl implements IOrderService {

    /**
     * 这是一个典型的创单场景
     * */
    private OrderDao orderDao;
    public OrderServiceImpl()
    {
        this.orderDao=new OrderDao();
    }
    @Override
    public int createOrder(OrderPojo order) {
        System.out.println("OrderService 调用orderDao 创建订单");
        return orderDao.insert(order);
    }
}
