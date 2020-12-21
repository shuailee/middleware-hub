package com.shuailee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @ServletComponentScan 默认只会扫描当前启动类所在包，也可以指定扫描跟目录；
 *      例如当启动类在/a/aa/mainapplication.java路径下，想要扫描它的上一级目录的同级目录b：a/b/test.java或a/test.java时，
 *      需要指定basePackages目录为a.aa或a @ComponentScan(basePackages = {"com.shuailee.a"})
 * @MapperScan("com.shuailee.dao") 此注解表示动态扫描DAO接口所在包,原理同上
 * */
@ServletComponentScan
@SpringBootApplication
@CrossOrigin(origins = "*", maxAge = 3600)
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
