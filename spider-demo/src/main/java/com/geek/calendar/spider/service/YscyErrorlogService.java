package com.geek.calendar.spider.service;

import com.geek.calendar.spider.model.ErrorlogInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: geek-calendar-spider
 * @description: ErrorlogService
 * @author: shuai.li
 * @create: 2020-04-17 17:08
 **/
@Mapper
public interface YscyErrorlogService {

    int insertErrorlog(ErrorlogInfo fate);
}
