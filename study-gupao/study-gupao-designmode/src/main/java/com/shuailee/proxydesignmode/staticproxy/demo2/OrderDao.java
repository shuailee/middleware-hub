package com.shuailee.proxydesignmode.staticproxy.demo2;

import com.shuailee.proxydesignmode.staticproxy.demo2.datasource.DynamicDataSourceEntry;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-04-25 16:20
 **/
public class OrderDao {

    public int insert(OrderPojo orderPojo){
        System.out.println("获取数据源成功：" + DynamicDataSourceEntry.get());
        System.out.println("OrderDao 创建Order 成功!");
        return 1;
    }
}
