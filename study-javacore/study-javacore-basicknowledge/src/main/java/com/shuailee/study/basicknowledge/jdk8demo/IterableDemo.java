package com.shuailee.study.basicknowledge.jdk8demo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @program: study-javacore-basicknowledge
 * @description:
 * @create: 2019-07-25 19:53
 **/
public class IterableDemo  {
    public static void main(String[] args) {
        //consumerdemo();
        //customdemo();

        new Thread(() -> System.out.print("123")).start();
    }



    public static void customdemo(){
        Animal a=cc->{
            System.out.println(cc);
        };
        a.say("hello");
        //方法引用可以这么写
        Animal b= System.out::println;
        b.say("你好");
    }

    public static void  consumerdemo()
    {
        List<String>  str= Arrays.asList("aa","bb");
        str.forEach(new Consumer<String>() {
            @Override
            public void accept(String t) {
                System.out.println(t);
            }
        });
        //等价于：
        str.forEach(s-> System.out.println(s));

    }

}


interface Animal{
    void say(String content);
}
