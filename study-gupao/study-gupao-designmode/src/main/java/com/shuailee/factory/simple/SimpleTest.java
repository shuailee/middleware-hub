package com.shuailee.factory.simple;

import com.shuailee.factory.YiliImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;


/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2018-10-25 15:40
 **/
public class SimpleTest {
    public static void main( String[] args )
    {
        //应用场景：又叫做静态工厂方法（StaticFactory Method）模式，但不属于23 种设计模式之一。
        //简单工厂模式的实质是由一个工厂类根据传入的参数，动态决定应该创建哪一个产品类
        System.out.println( "简单工厂：" );
        SimpleFactory factory=new SimpleFactory();
        System.out.println(factory.getMilk("m").getName());

        // 一般架构中是通过反射使用静态工厂的
        System.out.println(factory.getMilk(YiliImpl.class).getName());

    }
}
