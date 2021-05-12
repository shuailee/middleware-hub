package com.shuailee.study.basicknowledge.yanma;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @package: com.shuailee.study.basicknowledge.yanma
 * @description:
 * @author: klein
 * @date: 2021-05-11 19:24
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VirtualMobile {
    String conditionField() default "";
}
