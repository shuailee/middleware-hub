package com.shuailee.study.basicknowledge.annotationtest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据表注解
 * */
@Target(ElementType.TYPE)//只能应用到类上
@Retention(RetentionPolicy.RUNTIME)//保存到运行时
public  @interface DBTable{
    String name() default "";
}


