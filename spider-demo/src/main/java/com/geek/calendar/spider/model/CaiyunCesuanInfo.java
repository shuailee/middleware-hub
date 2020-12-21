package com.geek.calendar.spider.model;

import lombok.*;

import java.util.Date;

/**
 * @program: geek-calendar-spider
 * @description: CaiyunCesuanInfo
 * @author: shuai.li
 * @create: 2020-04-18 18:15
 **/
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@ToString
public class CaiyunCesuanInfo {
    Integer id;
    String sex;
    Integer year;
    Integer month;
    Integer day;
    Integer hour;
    Integer minute;
    Date birthday;
    String yscf_Pingfen;
    String yscf_Xiangjie;
    String zcy_Pingfen;
    String zcy_Xiangjie;
    String pcy_Pingfen;
    String pcy_Xiangjie;
    String pcjl_Pingfen;
    String pcjl_Xiangjie;
    String qncy_Pingfen;
    String qncy_Xiangjie;
    String zncy_Pingfen;
    String zncy_Xiangjie;
    String lncy_Pingfen;
    String lncy_Xiangjie;
    String createTime;

}
