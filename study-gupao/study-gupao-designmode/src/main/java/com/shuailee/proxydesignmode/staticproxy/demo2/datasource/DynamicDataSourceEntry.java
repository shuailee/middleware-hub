package com.shuailee.proxydesignmode.staticproxy.demo2.datasource;

/**
 * @program: study-gupao
 * @description: 数据源路由对象
 *
 * @author: shuai.li
 * @create: 2019-04-25 16:35
 **/
public class DynamicDataSourceEntry {
    // 默认数据源
    public final static String DEFAULT_SOURCE = null;
    //使用ThreadLocal 的单例实现
    private final static ThreadLocal<String> local = new ThreadLocal<String>();
    private DynamicDataSourceEntry(){}
    /**
     * 清空数据源
     */
    public static void clear() {
        local.remove();
    }
    /**
     * 获取当前正在使用的数据源名字
     *
     * @return String
     */
    public static String get() {
        return local.get();
    }
    /**
     * 还原当前切面的数据源
     */
    public static void restore() {
        local.set(DEFAULT_SOURCE);
    }
    /**
     * 设置已知名字的数据源
     *
     * @param source
     */
    public static void set(String source) {
        local.set(source);
    }
    /**
     * 根据年份动态设置数据源
     * @param year
     */
    public static void set(int year) {
        local.set("DB_" + year);
    }
}
