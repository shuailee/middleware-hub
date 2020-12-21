package com.shuailee.study.webmagicdemo.services;

import com.shuailee.study.webmagicdemo.model.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface INews {
    int insertNews (News news);
    void batchInsertNews(List<News> users);
}
