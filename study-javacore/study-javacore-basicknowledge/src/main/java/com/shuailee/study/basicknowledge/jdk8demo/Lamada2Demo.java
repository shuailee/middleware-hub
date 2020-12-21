package com.shuailee.study.basicknowledge.jdk8demo;

public class Lamada2Demo {

    public static void main(String[] args) {

        PrintString("test", (x) -> System.out.println(x));
    }

    public static void PrintString(String s, Print<String> print) {
        print.print(s);
    }
}

/**
 * 声明一个函数接口
 * */
@FunctionalInterface
interface Print<T>{
    public void print(T x);
}