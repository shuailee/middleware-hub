package com.shuailee.study.basicknowledge.yanma;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @package: com.shuailee.study.basicknowledge.yanma
 * @description:
 * @author: klein
 * @date: 2021-05-11 19:25
 **/
public class VirtualMobileProcessor {
    /**
     * 加密Bean对象中的指定字段<br>
     * 用于加密实体对象中的手机号码字段
     *
     * @param object Bean对象
     * @param clazz  对象类型
     * @return 加密之后的JavaBean
     */
    public static <T> T cryptoMobile(T object, Class<T> clazz) {
        String cryptoField;
        String conditionField;
        //判断是否有打了注解的字段
        for (Field declaredField : clazz.getDeclaredFields()) {
            VirtualMobile annotation = declaredField.getAnnotation(VirtualMobile.class);
            //找到注解字段,则执行字段加密
            if (Validator.isNotNull(annotation)) {
                cryptoField = declaredField.getName();
                conditionField = annotation.conditionField();
                return copyBean(cryptoField, conditionField, object, clazz);
            }
        }
        //没有找到注解字段,直接返回传入的对象
        return Convert.convert(clazz, object);
    }

    /**
     * 加密集合中每个对象的指定字段<br>
     * 用于加密实体对象中的手机号码字段
     *
     * @param collection Bean对象集合
     * @param clazz      集合中的对象类型
     * @return 加密之后的JavaBean集合
     */
    public static <T> Collection<T> cryptoMobileForList(Collection<T> collection, Class<T> clazz) {
        return collection.stream().map(t -> cryptoMobile(t, clazz)).collect(Collectors.toList());
    }

    /**
     * 加密Bean对象<br>
     * 传入需要被加密的Bean,返回加密之后的Bean
     *
     * @param cryptoField    需要被加密的字段
     * @param conditionField 参考是否加密的条件字段
     * @param object         Bean对象
     * @param clazz          对象类型
     * @return 加密之后的JavaBean
     */
    private static <T> T copyBean(String cryptoField, String conditionField, T object, Class<T> clazz) {
        try {
            //cryptoField参数为空,直接返回原对象
            if (Validator.isEmpty(cryptoField)) {
                return Convert.convert(clazz, object);
            }
            //判断条件字段
            if (Validator.isNotEmpty(conditionField)) {
                for (Field declaredField : clazz.getDeclaredFields()) {
                    declaredField.setAccessible(true);
                    //不加密
                    if (declaredField.getName().equals(conditionField) && Integer.valueOf(String.valueOf(declaredField.get(object))) == 0) {
                        return Convert.convert(clazz, object);
                    }
                    //加密
                    if (declaredField.getName().equals(conditionField) && Integer.valueOf(String.valueOf(declaredField.get(object))) == 1) {
                        return cryptoBean(cryptoField, object, clazz);
                    }
                }
            }
            //条件字段为空,强制加密
            return cryptoBean(cryptoField, object, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            //发生异常,返回原对象
            return Convert.convert(clazz, object);
        }
    }

    /**
     * 加密Bean中的指定字段
     *
     * @param cryptoField 需要被加密的字段
     * @param object      Bean对象
     * @param clazz       对象类型
     * @return 加密之后的JavaBean
     */
    private static <T> T cryptoBean(String cryptoField, T object, Class<T> clazz) {
        try {
            for (Field declaredField : clazz.getDeclaredFields()) {
                declaredField.setAccessible(true);
                if (declaredField.getName().equals(cryptoField)) {
                    BeanUtil.setFieldValue(object, cryptoField, createVirtualMobile(String.valueOf(declaredField.get(object))));
                }
            }
            return Convert.convert(clazz, object);
        } catch (Exception e) {
            e.printStackTrace();
            return Convert.convert(clazz, object);
        }
    }

    /**
     * 加密手机号码中间4位<br>
     * 如果不是11位手机号码,直接返回原值
     *
     * @param mobile 加密前的手机号码
     * @return 加密后的手机号码
     */
    private static String createVirtualMobile(String mobile) {
        try {
            //转换成Long如果异常或者不是11位,就说明不是手机号码,直接返回传入的对象
            Long.valueOf(mobile);
            if (mobile.length() != 11) {
                return mobile;
            }
            return StrUtil.hide(mobile, 3, 7);
        } catch (Exception e) {
            return mobile;
        }
    }

}
