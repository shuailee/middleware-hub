package com.geek.calendar.spider.utils;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.geek.calendar.spider.model.ZiWeiMingPanEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * @program: spider-demo
 * @description: FateUtil
 * @author: kelin
 * @create: 2020-04-15 15:45
 **/
public class FateUtil {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 运势爬虫field
     * */
    public static String getFieldName(ZiWeiMingPanEnum gong, String subName){
        return String.format("%s_%s", gong.name(), subName);
    }


    public static Calendar getCalendar(String time) throws ParseException {
        Date startDate = DATE_FORMAT.parse(time);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        return calendar;
    }

    /**
     * url参数转json
     * */
    public static String getJsonStrByQueryUrl(String paramStr){
        String[] params = paramStr.split("&");
        JSONObject obj = new JSONObject();
        for (int i = 0; i < params.length; i++) {
            String[] param = params[i].split("=");
            if (param.length >= 2) {
                String key = param[0];
                String value = param[1];
                for (int j = 2; j < param.length; j++) {
                    value += "=" + param[j];
                }
                try {
                    obj.put(key,value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj.toString();
    }


}
