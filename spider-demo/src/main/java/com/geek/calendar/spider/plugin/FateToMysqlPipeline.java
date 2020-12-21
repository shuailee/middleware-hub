package com.geek.calendar.spider.plugin;

import com.alibaba.fastjson.JSON;
import com.geek.calendar.spider.model.FateInfo;
import com.geek.calendar.spider.model.FateConst;
import com.geek.calendar.spider.model.ZiWeiMingPanEnum;
import com.geek.calendar.spider.service.FateService;
import com.geek.calendar.spider.utils.ConfigUtil;
import com.geek.calendar.spider.utils.DButils;
import com.geek.calendar.spider.utils.FateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.IOException;
import java.util.*;


/**
 * @program: geek-calendar-spider
 * @description: FateInfo
 * @author: shuai.li
 * @create: 2020-04-15 14:34
 **/
@Slf4j
public class FateToMysqlPipeline implements Pipeline {

    public FateToMysqlPipeline() {
    }

    public static List<FateInfo> synchronizedList = Collections.synchronizedList(new ArrayList<FateInfo>());


    @Override
    public void process(ResultItems resultitems, Task task) {

        FateInfo fateInfo = fateMap(resultitems);
        if (ConfigUtil.getInstance().isBatchCheck()) {
            blackInsert(fateInfo);
        } else {
            insertFate(fateInfo);
        }
    }

    /**
     * 单条插入
     */
    private void insertFate(FateInfo fateInfo) {
        SqlSession session = null;
        if (fateInfo != null) {
            try {
                session = DButils.getInstance().getSqlSessionFactory().openSession();
                FateService fateMapper = session.getMapper(FateService.class);
                fateMapper.insertFate(fateInfo);
                session.commit();
            } catch (IOException e) {
                log.info("fail data:" + JSON.toJSONString(fateInfo));
                log.error(e.toString());
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
    }


    /**
     * 批量插入
     */
    private void blackInsert(FateInfo fateInfo) {
        if (fateInfo != null) {
            synchronizedList.add(fateInfo);
        }
        int batch = ConfigUtil.getInstance().getBatchNum();
        if (synchronizedList.size() >= batch) {
            synchronized (FateToMysqlPipeline.class) {
                if (synchronizedList.size() >= batch) {
                    SqlSession session = null;
                    if (fateInfo != null) {
                        try {
                            session = DButils.getInstance().getSqlSessionFactory().openSession();
                            FateService fateMapper = session.getMapper(FateService.class);
                            fateMapper.batchInsertFates(synchronizedList);
                            session.commit();
                        } catch (IOException e) {
                            log.info("fail data:" + JSON.toJSONString(synchronizedList));
                            synchronizedList.clear();
                            log.error(e.toString());
                        } finally {
                            if (session != null) {
                                session.close();
                            }
                        }
                    }
                    log.info("本次写入数据量：" + synchronizedList.size());
                    synchronizedList.clear();

                }
            }
        }
    }

    /**
     * fate map
     */
    private FateInfo fateMap(ResultItems resultitems) {

        String parameter = new String(resultitems.getRequest().getRequestBody().getBody());
        if (parameter.indexOf("Init") > 0) {
            return null;
        }
        Map<String, Object> mapResults = resultitems.getAll();
        FateInfo fateInfo = null;
        if (mapResults != null) {
            try {
                fateInfo = new FateInfo();
                parameter = parameter.replace("%25", "").replace("%", "");
                HashMap<String, String> parameterMap = getRequestParameter(parameter);
                if (parameterMap.containsKey("Tablename")) {
                    fateInfo.setTableName("fate_2020_" + parameterMap.get("Tablename"));
                }
                fateInfo.setYear(Integer.valueOf(parameterMap.get(FateConst.PARAMETER_YEAR)));
                fateInfo.setMonth(Integer.valueOf(parameterMap.get(FateConst.PARAMETER_MONTH)));
                fateInfo.setDay(Integer.valueOf(parameterMap.get(FateConst.PARAMETER_DAY)));
                fateInfo.setHour(Integer.valueOf(parameterMap.get(FateConst.PARAMETER_HOUR)));
                fateInfo.setMinute(0);


                Calendar calendar = Calendar.getInstance();
                calendar.set(fateInfo.getYear(), fateInfo.getMonth() - 1, fateInfo.getDay(), fateInfo.getHour(), fateInfo.getMinute());
                fateInfo.setBirthday(calendar.getTime());

                String sex = mapResults.get(FateConst.GENDER).toString().trim().indexOf(FateConst.FEMALE) > -1 ? "F" : "M";
                fateInfo.setSex(sex);
                fateInfo.setShuXiang(mapResults.get(FateConst.SHUXIANG).toString());
                fateInfo.setWuXing(mapResults.get(FateConst.WUXING).toString());
                fateInfo.setShenZhu(mapResults.get(FateConst.SHENZHU).toString());
                fateInfo.setShenGong(mapResults.get(FateConst.SHENGONG).toString());
                fateInfo.setDouJun(mapResults.get(FateConst.DOUJUN).toString());
                fateInfo.setMingZhu(mapResults.get(FateConst.MINGZHU).toString());

                fateInfo.setNupuGong((String) mapResults.get(ZiWeiMingPanEnum.NUPU_GONG.name()));
                fateInfo.setNuPuGongXingXiu(getXingxiu(mapResults, ZiWeiMingPanEnum.NUPU_GONG));
                fateInfo.setQianYiGong((String) mapResults.get(ZiWeiMingPanEnum.QIANYI_GONG.name()));
                fateInfo.setQianYiGongXingXiu(getXingxiu(mapResults, ZiWeiMingPanEnum.QIANYI_GONG));
                fateInfo.setJiEGong((String) mapResults.get(ZiWeiMingPanEnum.JIE_GONG.name()));
                fateInfo.setJiEGongXingXiu(getXingxiu(mapResults, ZiWeiMingPanEnum.JIE_GONG));
                fateInfo.setCaiBoGong((String) mapResults.get(ZiWeiMingPanEnum.CAIBO_GONG.name()));
                fateInfo.setCaiBoGongXingXiu(getXingxiu(mapResults, ZiWeiMingPanEnum.CAIBO_GONG));
                fateInfo.setShiYeGong((String) mapResults.get(ZiWeiMingPanEnum.SHIYE_GONG.name()));
                fateInfo.setShiYeGongXingXiu(getXingxiu(mapResults, ZiWeiMingPanEnum.SHIYE_GONG));
                fateInfo.setZiNvGong((String) mapResults.get(ZiWeiMingPanEnum.ZINV_GONG.name()));
                fateInfo.setZiNvGongXingXiu(getXingxiu(mapResults, ZiWeiMingPanEnum.ZINV_GONG));
                fateInfo.setTianZhaiGong((String) mapResults.get(ZiWeiMingPanEnum.TIANZHAI_GONG.name()));
                fateInfo.setTianZhaiGongXingXiu(getXingxiu(mapResults, ZiWeiMingPanEnum.TIANZHAI_GONG));
                fateInfo.setFuQiGong((String) mapResults.get(ZiWeiMingPanEnum.FUQI_GONG.name()));
                fateInfo.setFuQiGongXingXiu(getXingxiu(mapResults, ZiWeiMingPanEnum.FUQI_GONG));
                fateInfo.setFuDeGong((String) mapResults.get(ZiWeiMingPanEnum.FUDE_GONG.name()));
                fateInfo.setFudeGongXingXiu(getXingxiu(mapResults, ZiWeiMingPanEnum.FUDE_GONG));
                fateInfo.setFuMuGong((String) mapResults.get(ZiWeiMingPanEnum.FUMU_GONG.name()));
                fateInfo.setFuMuGongXingXiu(getXingxiu(mapResults, ZiWeiMingPanEnum.FUMU_GONG));
                fateInfo.setMingGong((String) mapResults.get(ZiWeiMingPanEnum.MINGGONG_GONG.name()));
                fateInfo.setMingGongXingXiu(getXingxiu(mapResults, ZiWeiMingPanEnum.MINGGONG_GONG));
                fateInfo.setXiongDiGong((String) mapResults.get(ZiWeiMingPanEnum.XIONGDI_GONG.name()));
                fateInfo.setXiongDiGongXingXiu(getXingxiu(mapResults, ZiWeiMingPanEnum.XIONGDI_GONG));
            } catch (Exception ex) {
                log.error("錯誤：" + ex.toString() + ";參數：" + parameter);
            }
        }
        return fateInfo;
    }

    /**
     * desc: getXingxiu
     *
     * @param: [mapResults, ziWeiMingPanEnum]
     * @return: java.lang.String
     */
    private String getXingxiu(Map<String, Object> mapResults, ZiWeiMingPanEnum ziWeiMingPanEnum) {
        String xingxiu1 = mapResults.get(FateUtil.getFieldName(ziWeiMingPanEnum, FateConst.XINGXIU1)).toString();
        String xingxiu2 = mapResults.get(FateUtil.getFieldName(ziWeiMingPanEnum, FateConst.XINGXIU2)).toString();
        String xingxiu3 = mapResults.get(FateUtil.getFieldName(ziWeiMingPanEnum, FateConst.XINGXIU3)).toString();
        String xingxiu4 = mapResults.get(FateUtil.getFieldName(ziWeiMingPanEnum, FateConst.XINGXIU4)).toString();
        String ganzhi = mapResults.get(FateUtil.getFieldName(ziWeiMingPanEnum, FateConst.GANZHI)).toString();
        ganzhi = ganzhi.replace("【", "").replace("】", "");
        return String.format("%s,%s,%s,%s,%s", xingxiu1, xingxiu2, xingxiu3, xingxiu4, ganzhi);
    }

    /**
     * 获取请求参数
     */
    private HashMap<String, String> getRequestParameter(String parameterBody) {
        String[] parameter = parameterBody.split("&");
        HashMap<String, String> timeMap = new HashMap<>();
        for (String item : parameter) {
            String[] itemArr = item.split("=");
            if (itemArr[0] != null && itemArr[1] != null) {
                timeMap.put(itemArr[0], itemArr[1]);
            }
        }
        return timeMap;
    }


    /**
     * desc: flush 持久化缓冲区数据
     *
     * @param: []
     * @return: void
     */
    public void flush() {

        log.info("start flush......");
        try {
            if (FateToMysqlPipeline.synchronizedList != null && FateToMysqlPipeline.synchronizedList.size() > 0) {
                SqlSession session = DButils.getInstance().getSqlSessionFactory().openSession();
                FateService fateMapper = session.getMapper(FateService.class);
                fateMapper.batchInsertFates(FateToMysqlPipeline.synchronizedList);
                session.commit();
            }
        } catch (Exception ex) {
            log.info("flush error:" + JSON.toJSONString(FateToMysqlPipeline.synchronizedList));
        }
        log.info("flush sucess......");
    }


}