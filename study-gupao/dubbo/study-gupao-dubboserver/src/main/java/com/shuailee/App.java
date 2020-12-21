package com.shuailee;

import com.alibaba.dubbo.container.Main;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        // 通过spring的方式加载dubbo的配置文件
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("dubbo-server.xml");
        context.start();
        System.in.read();

        //dubbo的启动类方法 启动多个container
        //Main.main(args);
        //Main.main(new String[]{"spring","log4j"});

    }
}
