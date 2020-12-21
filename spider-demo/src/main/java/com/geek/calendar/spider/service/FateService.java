package com.geek.calendar.spider.service;

import com.geek.calendar.spider.model.FateInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: geek-calendar-spider
 * @description: FateService
 * @author: shuai.li
 * @create: 2020-04-16 11:11
 **/
@Mapper
public interface FateService {
    int insertFate (FateInfo fateInfo);
    void batchInsertFates(List<FateInfo> fateInfos);
    void insertFateToTable(FateInfo fateInfo);
    List<FateInfo> query(FateInfo fateInfo);
}
