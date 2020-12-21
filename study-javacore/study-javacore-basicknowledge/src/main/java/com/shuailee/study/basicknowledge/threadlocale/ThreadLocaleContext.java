package com.shuailee.study.basicknowledge.threadlocale;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: study-javacore
 * @description:ThreadLocale 实践
 * @create: 2019-10-28 10:38
 **/
public class ThreadLocaleContext {


    private static final int CONTEXT_DEFAULT_SIZE = 1 << 6;

    private static final ThreadLocal<Map<String, Object>> CONTEXT = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new ConcurrentHashMap<>(CONTEXT_DEFAULT_SIZE);
        }
    };

    /**
     * 添加上下文数据
     *
     * @param key
     * @param value
     * @param <T>
     */
    public static <T> void add(String key, T value) {
        if (CONTEXT.get().containsKey(key))
            remove(key);

        CONTEXT.get().put(key, value);
    }

    /**
     * 获取上线文中字典指定数据
     *
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T get(String key) {
        return (T) CONTEXT.get().get(key);
    }

    /**
     * 移除指定上下字典中的KEY
     *
     * @param key
     * @return
     */
    public static Object remove(String key) {
        return CONTEXT.get().remove(key);
    }

    /**
     * 获取当前上下文数据
     *
     * @return
     */
    public static Map<String, Object> get() {
        return CONTEXT.get();
    }

    /**
     * 清除当前上下文了数据
     */
    public static void clear() {
        CONTEXT.get().clear();
    }

    /**
     * 释放当前上下文
     */
    public static void release() {
        CONTEXT.remove();
    }
}
