package com.geek.calendar.spider.service;

import com.geek.calendar.spider.model.ErrorlogInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: spider-demo
 * @description: ErrorlogService
 * @author: kelin
 * @create: 2020-04-17 17:08
 **/
@Mapper
public interface YscyErrorlogService {

    int insertErrorlog(ErrorlogInfo fate);
}
