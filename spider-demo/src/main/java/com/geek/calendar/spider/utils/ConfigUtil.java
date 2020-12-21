package com.geek.calendar.spider.utils;

import lombok.Data;

import java.util.Properties;

/**
 * @program: geek-calendar-spider
 * @description: ConfigUtil
 * @author: shuai.li
 * @create: 2020-04-16 16:15
 **/
@Data
public class ConfigUtil {

    String startTime;
    String endTime;
    int sleepTime;
    int threadNum;
    boolean batchCheck;
    int batchNum;

    private volatile static ConfigUtil lazy = null;

    private ConfigUtil() {
        loadConfig();
    }

    public static ConfigUtil getInstance() {
        if (lazy == null) {
            synchronized (ConfigUtil.class) {
                if (lazy == null) {
                    lazy = new ConfigUtil();
                }
            }
        }
        return lazy;
    }


    private void loadConfig() {
        try {
            Properties prop = new Properties();
            prop.load(this.getClass().getClassLoader().getResourceAsStream("SpiderConfig.properties"));
            this.startTime = prop.getProperty("spider.startTime");
            this.endTime = prop.getProperty("spider.endTime");
            this.sleepTime = Integer.valueOf(prop.getProperty("spider.sleepTime"));
            this.threadNum = Integer.valueOf(prop.getProperty("spider.threadNum"));
            this.batchCheck = Boolean.valueOf(prop.getProperty("spider.batchCheck"));
            this.batchNum = Integer.valueOf(prop.getProperty("spider.batchNum"));
        } catch (Exception ex) {
            System.out.println("load config fail...");
        }
    }

}
