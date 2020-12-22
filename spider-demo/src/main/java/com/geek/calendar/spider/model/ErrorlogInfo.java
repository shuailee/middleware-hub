package com.geek.calendar.spider.model;

import lombok.*;

/**
 * @program: spider-demo
 * @description: ErrorlogInfo
 * @author: kelin
 * @create: 2020-04-17 16:37
 **/
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@ToString
public class ErrorlogInfo {
    Integer id;
    String url;
    String errortype;
    String errorcode;
    String parameter;
}
