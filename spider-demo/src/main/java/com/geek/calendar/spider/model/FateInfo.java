package com.geek.calendar.spider.model;

import lombok.*;

import java.util.Date;

/**
 * @program: geek-calendar-spider
 * @description: FateInfo
 * @author: shuai.li
 * @create: 2020-04-15 14:34
 **/
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@ToString
public class FateInfo {
    public Integer id;
    public String sex;
    public Integer year;
    public Integer month;
    public Integer day;
    public Integer hour;
    public Integer minute;
    public Date birthday;
    public String shuXiang;
    public String shenGong;
    public String wuXing;
    public String mingZhu;
    public String douJun;
    public String shenZhu;
    public String nupuGong;
    public String qianYiGong;
    public String jiEGong;
    public String caiBoGong;
    public String shiYeGong;
    public String tianZhaiGong;
    public String fuDeGong;
    public String fuMuGong;
    public String mingGong;
    public String xiongDiGong;
    public String fuQiGong;
    public String ziNvGong;
    public String nuPuGongXingXiu;
    public String qianYiGongXingXiu;
    public String jiEGongXingXiu;
    public String caiBoGongXingXiu;
    public String shiYeGongXingXiu;
    public String tianZhaiGongXingXiu;
    public String fudeGongXingXiu;
    public String fuMuGongXingXiu;
    public String mingGongXingXiu;
    public String xiongDiGongXingXiu;
    public String fuQiGongXingXiu;
    public String ziNvGongXingXiu;

    public String tableName;
}
