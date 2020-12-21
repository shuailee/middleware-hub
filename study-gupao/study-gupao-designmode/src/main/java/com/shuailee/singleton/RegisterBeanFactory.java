package com.shuailee.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册模式单例
 */
public class RegisterBeanFactory {

    private RegisterBeanFactory() {
    }
    /**
     * 线程安全的map
     * */
    private static Map<String, Object> ioc = new ConcurrentHashMap<String, Object>();

    /**
     * 线程安全的单例
     * */
    public static synchronized Object genBean(String className) {
        if (!ioc.containsKey(className)) {
            Object obj = null;
            try {
                obj = Class.forName(className).newInstance();
                ioc.put(className, obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }else {
            return ioc.get(className);
        }
    }
}
