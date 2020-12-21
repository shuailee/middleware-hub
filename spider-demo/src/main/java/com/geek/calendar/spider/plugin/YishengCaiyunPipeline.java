package com.geek.calendar.spider.plugin;

import com.alibaba.fastjson.JSON;
import com.geek.calendar.spider.model.CaiyunCesuanInfo;
import com.geek.calendar.spider.model.FateConst;
import com.geek.calendar.spider.service.CaiyunCesuanService;
import com.geek.calendar.spider.utils.DButils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.IOException;
import java.util.*;

/**
 * @program: geek-calendar-spider
 * @description: YishengCaiyunPipeline
 * @author: shuai.li
 * @create: 2020-04-18 18:14
 **/
@Slf4j
public class YishengCaiyunPipeline implements Pipeline {


    public static List<CaiyunCesuanInfo> synchronizedList = Collections.synchronizedList(new ArrayList<CaiyunCesuanInfo>());

    @Override
    public void process(ResultItems resultitems, Task task) {

        CaiyunCesuanInfo caiyunCesuanInfo = caiyunCesuanMap(resultitems);

        if (caiyunCesuanInfo != null) {
            synchronizedList.add(caiyunCesuanInfo);
        }
        if (synchronizedList.size() >= 100) {
            synchronized (YishengCaiyunPipeline.class) {
                if (synchronizedList.size() >= 100) {
                    SqlSession session = null;
                    if (caiyunCesuanInfo != null) {
                        try {
                            session = DButils.getInstance().getSqlSessionFactory().openSession();
                            CaiyunCesuanService fateMapper = session.getMapper(CaiyunCesuanService.class);
                            fateMapper.batchCaiyunCesuan(synchronizedList);
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
    private CaiyunCesuanInfo caiyunCesuanMap(ResultItems resultitems) {
        String parameter = new String(resultitems.getRequest().getRequestBody().getBody());
        if (parameter.indexOf("Init") > -1) {
            return null;
        }
        Map<String, Object> mapResults = resultitems.getAll();
        CaiyunCesuanInfo caiyun = null;
        if (mapResults != null) {
            try {
                caiyun = new CaiyunCesuanInfo();
                HashMap<String, String> parameterMap = getRequestParameter(parameter);
                String[] date = parameterMap.get("date").split("-");
                caiyun.setYear(Integer.valueOf(date[0]));
                caiyun.setMonth(Integer.valueOf(date[2]));
                caiyun.setDay(Integer.valueOf(date[3]));
                caiyun.setHour(Integer.valueOf(parameterMap.get("hour")));
                caiyun.setMinute(0);

                Calendar calendar = Calendar.getInstance();
                calendar.set(caiyun.getYear(), caiyun.getMonth() - 1, caiyun.getDay(), caiyun.getHour(), caiyun.getMinute());
                caiyun.setBirthday(calendar.getTime());

                String sex = mapResults.get(FateConst.GENDER).toString().trim().indexOf(FateConst.FEMALE) > -1 ? "F" : "M";
                caiyun.setSex(sex);
                caiyun.setYscf_Pingfen(mapResults.get(FateConst.YSCF_PINGFEN).toString());
                caiyun.setYscf_Xiangjie(mapResults.get(FateConst.YSCF_XIANGJIE).toString());

                caiyun.setZcy_Pingfen(mapResults.get(FateConst.ZCY_PINGFEN).toString());
                caiyun.setZcy_Xiangjie(mapResults.get(FateConst.ZCY_XIANGJIE).toString());

                caiyun.setPcy_Pingfen(mapResults.get(FateConst.PCY_PINGFEN).toString());
                caiyun.setPcy_Xiangjie(mapResults.get(FateConst.PCY_XIANGJIE).toString());

                caiyun.setPcjl_Pingfen(mapResults.get(FateConst.POCAI_PINGFEN).toString());
                caiyun.setPcjl_Xiangjie(mapResults.get(FateConst.POCAI_XIANGJIE).toString());

                caiyun.setQncy_Pingfen(mapResults.get(FateConst.QNCY_PINGFEN).toString());
                caiyun.setQncy_Xiangjie(mapResults.get(FateConst.QNCY_XIANGJIE).toString());

                caiyun.setZncy_Pingfen(mapResults.get(FateConst.ZNCY_PINGFEN).toString());
                caiyun.setZncy_Xiangjie(mapResults.get(FateConst.ZNCY_XIANGJIE).toString());

                caiyun.setLncy_Pingfen(mapResults.get(FateConst.LNCY_PINGFEN).toString());
                caiyun.setLncy_Xiangjie(mapResults.get(FateConst.LNCY_XIANGJIE).toString());
            } catch (Exception ex) {
                log.error("錯誤：" + ex.toString() + ";參數：" + parameter);
            }
        }
        return caiyun;
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
}
