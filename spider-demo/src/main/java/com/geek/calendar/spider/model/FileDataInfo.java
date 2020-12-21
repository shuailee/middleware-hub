package com.geek.calendar.spider.model;

import lombok.Data;

/**
 * @program: geek-calendar-spider
 * @description: FileDataInfo
 * @author: shuai.li
 * @create: 2020-04-22 14:47
 **/
@Data
public class FileDataInfo {

    Integer year;
    Integer month;
    Integer day;
    Integer hour;
    String sex;
    String tableName;


}
