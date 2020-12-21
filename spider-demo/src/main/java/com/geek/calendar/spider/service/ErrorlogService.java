package com.geek.calendar.spider.service;

import com.geek.calendar.spider.model.ErrorlogInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: geek-calendar-spider
 * @description: ErrorlogService
 * @author: shuai.li
 * @create: 2020-04-17 17:08
 **/
@Mapper
public interface ErrorlogService {

    int insertErrorlog (ErrorlogInfo fate);

    int updateLogStatus(String parameter);

    List<String> queryParameter(String year);


}
