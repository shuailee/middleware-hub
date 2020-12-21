package com.geek.calendar.spider;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Hello world!
 */
@Slf4j
public class App {
    public static void main(String[] args) throws ParseException {

       int hous = betweenHour("1931-1-1 1:00:00","1939-12-31 23:00:00");
        System.out.println(hous);


    }

    private static int  betweenHour(String st,String e) {
        /*Calendar calendarStart = getCalendar("1931-1-1 1:00:00");
        Calendar calendarEnd = getCalendar("1939-12-31 23:00:00");*/
        Calendar calendarStart = getCalendar(st);
        Calendar calendarEnd = getCalendar(e);

        long days = (calendarEnd.getTimeInMillis() - calendarStart.getTimeInMillis()) / (1000 * 60 * 60 * 24)+1;

        return (int)days*24;
    }


    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");



    public static Calendar getCalendar(String time)  {
        Date startDate = null;
        try {
            startDate = DATE_FORMAT.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        return calendar;
    }

}
