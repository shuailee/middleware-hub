package com.geek.calendar.spider.service;

import com.geek.calendar.spider.model.CaiyunCesuanInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: spider-demo
 * @description: FateService
 * @author: kelin
 * @create: 2020-04-16 11:11
 **/
@Mapper
public interface CaiyunCesuanService {
    int insertCaiyunCesuan(CaiyunCesuanInfo caiyunCesuanInfo);
    void batchCaiyunCesuan(List<CaiyunCesuanInfo> caiyunCesuanInfo);
}
