package com.geek.calendar.spider.spilder.fate;

import com.geek.calendar.spider.model.FateConst;
import com.geek.calendar.spider.model.ZiWeiMingPanEnum;
import com.geek.calendar.spider.utils.ConfigUtil;
import com.geek.calendar.spider.utils.FateUtil;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: geek-calendar-spider
 * @description: BaseFateProcessor
 * @author: shuai.li
 * @create: 2020-04-22 17:13
 **/
@Slf4j
public class BaseFateProcessor implements PageProcessor {

    /**
     * https://chinafree.gitbooks.io/webmagic/content/zh/posts/ch4-basic-page-processor/spider-config.html
     * */
    private Site site = Site.me()
            //设置超时时间
            .setTimeOut(5000)
            //设置重试给次数
            .setRetryTimes(3)
            //设置循环重试给次数
            .setCycleRetryTimes(5)
            .setSleepTime(ConfigUtil.getInstance().getSleepTime());

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {

    }


    /**
     * desc: putFields
     *
     * @param: [page]
     * @return: void
     */
    protected void pageAnalysis(Page page) {
        HashMap<ZiWeiMingPanEnum, String> mapPath = getMingPanPath();
        for (Map.Entry<ZiWeiMingPanEnum, String> mingPanPath : mapPath.entrySet()) {
            ZiWeiMingPanEnum key = mingPanPath.getKey();
            String path = mingPanPath.getValue();
            page.putField(key.name()
                    , page.getHtml().xpath(path + "/tr[10]/td/table/tbody/tr/td/font/text()").toString());
            page.putField(FateUtil.getFieldName(key, FateConst.XINGXIU1)
                    , page.getHtml().xpath(path + "/tr[1]/td[6]/font/text()").toString());
            page.putField(FateUtil.getFieldName(key, FateConst.XINGXIU2)
                    , page.getHtml().xpath(path + "/tr[1]/td[7]/font/text()").toString());
            page.putField(FateUtil.getFieldName(key, FateConst.XINGXIU3)
                    , page.getHtml().xpath(path + "/tr[1]/td[8]/font/text()").toString());
            page.putField(FateUtil.getFieldName(key, FateConst.XINGXIU4)
                    , page.getHtml().xpath(path + "/tr[1]/td[9]/font/text()").toString());
            page.putField(FateUtil.getFieldName(key, FateConst.GANZHI)
                    , page.getHtml().xpath(path + "/tr[7]/td[7]/font/b/font/text()").toString());
        }

        // 个人信息
        page.putField(FateConst.SHENGONG
                , page.getHtml().xpath(FateConst.ZWMP_CENTERINFO_PATH + "/tr[4]/td[2]/span/text()").replace("：", ""));
        page.putField(FateConst.WUXING
                , page.getHtml().xpath(FateConst.ZWMP_CENTERINFO_PATH + "/tr[5]/td[2]/span/text()").replace("：", ""));
        page.putField(FateConst.DOUJUN
                , page.getHtml().xpath(FateConst.ZWMP_CENTERINFO_PATH + "/tr[5]/td[4]/span/text()").replace("：", ""));
        page.putField(FateConst.MINGZHU
                , page.getHtml().xpath(FateConst.ZWMP_CENTERINFO_PATH + "/tr[6]/td[2]/span/text()").replace("：", ""));
        page.putField(FateConst.SHENZHU
                , page.getHtml().xpath(FateConst.ZWMP_CENTERINFO_PATH + "/tr[6]/td[4]/span/text()").replace("：", ""));
        String gender = FateConst.FADE_BASEXPATH + "/tr[2]/td[2]/text()";
        String shengxiao = FateConst.FADE_BASEXPATH + "/tr[3]/td[4]/a/text()";
        page.putField(FateConst.GENDER, page.getHtml().xpath(gender).toString());
        page.putField(FateConst.SHUXIANG, page.getHtml().xpath(shengxiao).toString());

    }

    /**
     * 紫薇命盘十二宫xpath
     */
    private HashMap<ZiWeiMingPanEnum, String> getMingPanPath() {

        HashMap<ZiWeiMingPanEnum, String> pathMap = new HashMap<>(12);
        pathMap.put(ZiWeiMingPanEnum.NUPU_GONG, FateConst.ZWMP_BASEXPATH + "/tr[1]/td[1]/table/tbody");
        pathMap.put(ZiWeiMingPanEnum.QIANYI_GONG, FateConst.ZWMP_BASEXPATH + "/tr[1]/td[2]/table/tbody");
        pathMap.put(ZiWeiMingPanEnum.JIE_GONG, FateConst.ZWMP_BASEXPATH + "/tr[1]/td[3]/table/tbody");
        pathMap.put(ZiWeiMingPanEnum.CAIBO_GONG, FateConst.ZWMP_BASEXPATH + "/tr[1]/td[4]/table/tbody");

        pathMap.put(ZiWeiMingPanEnum.SHIYE_GONG, FateConst.ZWMP_BASEXPATH + "/tr[2]/td[1]/table/tbody");
        pathMap.put(ZiWeiMingPanEnum.ZINV_GONG, FateConst.ZWMP_BASEXPATH + "/tr[2]/td[3]/table/tbody");

        pathMap.put(ZiWeiMingPanEnum.TIANZHAI_GONG, FateConst.ZWMP_BASEXPATH + "/tr[3]/td[1]/table/tbody");
        pathMap.put(ZiWeiMingPanEnum.FUQI_GONG, FateConst.ZWMP_BASEXPATH + "/tr[3]/td[2]/table/tbody");

        pathMap.put(ZiWeiMingPanEnum.FUDE_GONG, FateConst.ZWMP_BASEXPATH + "/tr[4]/td[1]/table/tbody");
        pathMap.put(ZiWeiMingPanEnum.FUMU_GONG, FateConst.ZWMP_BASEXPATH + "/tr[4]/td[2]/table/tbody");
        pathMap.put(ZiWeiMingPanEnum.MINGGONG_GONG, FateConst.ZWMP_BASEXPATH + "/tr[4]/td[3]/table/tbody");
        pathMap.put(ZiWeiMingPanEnum.XIONGDI_GONG, FateConst.ZWMP_BASEXPATH + "/tr[4]/td[4]/table/tbody");

        return pathMap;
    }




    protected void addTargetRequestList(Page page) throws ParseException {
        Calendar calendarStart = FateUtil.getCalendar(ConfigUtil.getInstance().getStartTime());
        Calendar calendarEnd = FateUtil.getCalendar(ConfigUtil.getInstance().getEndTime());

        boolean flag = true;
        while (flag) {
            int year = calendarStart.get(Calendar.YEAR);
            int month = calendarStart.get(Calendar.MONTH);
            int day = calendarStart.get(Calendar.DAY_OF_MONTH);
            int hour = calendarStart.get(Calendar.HOUR_OF_DAY);
            flag = calendarStart.compareTo(calendarEnd) <= 0;
            //flag = year <= 2002 && month <= 12 && day <= 31 && hour <= 23;
            if (!flag) {
                break;
            }

            calendarStart.add(Calendar.HOUR, 1);
            // 24小时只跑奇数 == 12时辰
            if (hour % 2 == 0) {
                continue;
            }
            for (int i = 0; i < 2; i++) {
                Request request = new Request("http://www.99166.com/zt/2020/result.asp");
                request.setMethod(HttpConstant.Method.POST);
                Map<String, Object> params = new HashMap<>(5);
                params.put(FateConst.PARAMETER_SEX, i == 0 ? "male" : "female");
                params.put(FateConst.PARAMETER_YEAR, year);
                params.put(FateConst.PARAMETER_MONTH, month + 1);
                params.put(FateConst.PARAMETER_DAY, day);
                params.put(FateConst.PARAMETER_HOUR, hour);
                request.setRequestBody(HttpRequestBody.form(params, "utf-8"));
                page.addTargetRequest(request);
            }
        }

    }


    /**
     * post请求
     */
    protected Request buildRequest() {
        Request request = new Request("http://www.99166.com/zt/2020/result.asp");
        request.setMethod(HttpConstant.Method.POST);
        Map<String, Object> params = new HashMap<>(6);
        params.put(FateConst.PARAMETER_SEX, "female");
        params.put(FateConst.PARAMETER_YEAR, 1930);
        params.put(FateConst.PARAMETER_MONTH, 1);
        params.put(FateConst.PARAMETER_DAY, 1);
        params.put(FateConst.PARAMETER_HOUR, 0);
        params.put("Init", true);
        request.setRequestBody(HttpRequestBody.form(params, "utf-8"));
        return request;
    }
}
