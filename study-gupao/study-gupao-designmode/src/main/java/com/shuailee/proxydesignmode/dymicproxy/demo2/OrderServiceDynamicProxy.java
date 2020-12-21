package com.shuailee.proxydesignmode.dymicproxy.demo2;

import com.shuailee.proxydesignmode.staticproxy.demo2.IOrderService;
import com.shuailee.proxydesignmode.staticproxy.demo2.OrderPojo;
import com.shuailee.proxydesignmode.staticproxy.demo2.OrderServiceImpl;
import com.shuailee.proxydesignmode.staticproxy.demo2.datasource.DynamicDataSourceEntry;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: study-gupao
 * @description: jdk动态代理实现多数据源切换
 * @author: shuai.li
 * @create: 2019-04-25 21:37
 **/
public class OrderServiceDynamicProxy implements InvocationHandler {

/**
 * 动态代理实现之后，我们不仅能实现Order 的数据
 源动态路由，还可以实现其他任何类的数据源路由
 * */
    public static void main(String[] args) {
        try {
            OrderPojo order = new OrderPojo();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date date = sdf.parse("2018/02/01");
            order.setCreateTime(date.getTime());
            IOrderService orderService = (IOrderService)new OrderServiceDynamicProxy().getInstance(new OrderServiceImpl());
            orderService.createOrder(order);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private Object target;
    public Object getInstance(Object target){
        this.target = target;
        Class<?> clazz = target.getClass();
        //类加载，将类加载到jvm：classLoader.loadClass()
        //Class.forName()：将类的.class文件加载到jvm中，还会对类进行解释，执行类中的static块；使用Class.forName()来加载类，默认会执行初始化块
        //ClassLoader.loadClass()：只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容不会执行初始化块,只有在newInstance才会去执行static块。
        //Proxy主要作用是通过字节码重组生成一个新的对象：(类加载器，对象接口，处理器）
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(args[0]);
        Object object = method.invoke(target,args);
        after();
        return object;
    }

    private void before(Object target) {
        try {
            System.out.println("Proxy before method.");
            //通过反射调用getCreateTime 方法
            Long time = (Long) target.getClass().getMethod("getCreateTime").invoke(target);
            Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
            System.out.println("动态代理类自动分配到【DB_" + dbRouter + "】数据源处理数据。");
            DynamicDataSourceEntry.set(dbRouter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void after() {
        System.out.println("Proxy after method.");
    }
}
