package com.shuailee.study.gdpr;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shuailee.study.tuomin.SensitiveInfoSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = GdprSerializer.class) // 数据转换 https://www.cnblogs.com/luxianyu-s/p/9640588.html
@JacksonAnnotationsInside  //@JacksonAnnotationsInside：表示将注解捆绑，然后定义一个捆绑后的注解。
public @interface GdprField {
}
