package com.shuailee.study.basicknowledge.annotationtest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 约束注解
 * */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraints{
    boolean primarykey() default false;
    boolean allowNull() default true;
    boolean unique() default false;
}
