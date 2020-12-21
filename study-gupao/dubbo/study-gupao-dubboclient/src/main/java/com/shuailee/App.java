package com.shuailee;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.applet.Main;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("dubbo-client.xml");
        context.start();
        IHello hello= (IHello)context.getBean("helloService");
        System.out.println(hello.sayHello("mix"));


        IPay payService= (IPay)context.getBean("payService");
        System.out.println("嘿嘿嘿" + payService.payService(10));
    }
}
