package com.geek.calendar.spider.spilder;

import com.alibaba.fastjson.JSON;
import com.geek.calendar.spider.model.FateConst;
import com.geek.calendar.spider.plugin.YishengCaiyunPipeline;
import com.geek.calendar.spider.plugin.YishengMyDownload;
import com.geek.calendar.spider.service.CaiyunCesuanService;
import com.geek.calendar.spider.utils.ConfigUtil;
import com.geek.calendar.spider.utils.DButils;
import com.geek.calendar.spider.utils.FateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;
import java.text.ParseException;
import java.util.*;

/**
 * @program: geek-calendar-spider
 * @description: YiShengCaifuProcessor
 * @author: shuai.li
 * @create: 2020-04-17 22:00
 **/
@Slf4j
public class YiShengCaifuProcessor implements PageProcessor {

    private static String starttime;


    private Site site = Site.me().setUserAgent("Mozilla/5.0").setRetryTimes(3).setSleepTime(ConfigUtil.getInstance().getSleepTime());


    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        if(page.getRequest()!=null) {
            String parameter = new String(page.getRequest().getRequestBody().getBody());
            log.info("请求参数:" + parameter);
            if (parameter.indexOf("Init") > 0) {
                try {
                    addTargetRequestList(page);
                    log.info("请求次数:" + (page.getTargetRequests() != null ? page.getTargetRequests().size() : 0));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                pageAnalysis(page);
            }
        }
    }


    /**
     * desc: putFields
     *
     * @param: [page]
     * @return: void
     */
    private void pageAnalysis(Page page) {


        page.putField(FateConst.GENDER, page.getHtml().xpath("/html/body/div[1]/div[2]/div[2]/div[4]/dl/dd[1]/text()").toString());

        page.putField(FateConst.YSCF_PINGFEN, page.getHtml().xpath("//*[@id=\"list\"]/dd[3]/p[1]/span/text()").toString());
        page.putField(FateConst.YSCF_XIANGJIE, page.getHtml().xpath("//*[@id=\"list\"]/dd[3]/p[2]/text()").toString());

        page.putField(FateConst.ZCY_PINGFEN, page.getHtml().xpath("//*[@id=\"list\"]/dd[4]/p[1]/span/text()").toString());
        page.putField(FateConst.ZCY_XIANGJIE, page.getHtml().xpath("//*[@id=\"list\"]/dd[4]/p[2]/text()").toString());

        page.putField(FateConst.PCY_PINGFEN, page.getHtml().xpath("//*[@id=\"list\"]/dd[5]/p[1]/span/text()").toString());
        page.putField(FateConst.PCY_XIANGJIE, page.getHtml().xpath("//*[@id=\"list\"]/dd[5]/p[2]/text()").toString());

        page.putField(FateConst.POCAI_PINGFEN, page.getHtml().xpath("//*[@id=\"list\"]/dd[6]/p[1]/span/text()").toString());
        page.putField(FateConst.POCAI_XIANGJIE, page.getHtml().xpath("//*[@id=\"list\"]/dd[6]/p[2]/text()").toString());

        page.putField(FateConst.QNCY_PINGFEN, page.getHtml().xpath("//*[@id=\"list\"]/dd[7]/p[1]/span/text()").toString());
        page.putField(FateConst.QNCY_XIANGJIE, page.getHtml().xpath("//*[@id=\"list\"]/dd[7]/p[2]/text()").toString());

        page.putField(FateConst.ZNCY_PINGFEN, page.getHtml().xpath("//*[@id=\"list\"]/dd[8]/p[1]/span/text()").toString());
        page.putField(FateConst.ZNCY_XIANGJIE, page.getHtml().xpath("//*[@id=\"list\"]/dd[8]/p[2]/text()").toString());

        page.putField(FateConst.LNCY_PINGFEN, page.getHtml().xpath("//*[@id=\"list\"]/dd[9]/p[1]/span/text()").toString());
        page.putField(FateConst.LNCY_XIANGJIE, page.getHtml().xpath("//*[@id=\"list\"]/dd[9]/p[2]/text()").toString());

    }


    private void addTargetRequestList(Page page) throws ParseException {
        Calendar calendarStart = FateUtil.getCalendar(YiShengCaifuProcessor.starttime);
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
                Request request = new Request("https://www.dfsmw.com/caiyun/");
                request.setMethod(HttpConstant.Method.POST);
                try {
                    Map<String, Object> params = new HashMap<>(5);

                    // 0 女 1 男
                    params.put("sex", String.valueOf(i));
                    //0 农历, 2 公历   "2001-2-2-27"
                    params.put("date", String.format("%d-%d-%d-%d", year, 2, month + 1, day));
                    params.put("hour", String.valueOf(hour));

                    request.setRequestBody(HttpRequestBody.form(params, "utf-8"));
                    page.addTargetRequest(request);
                } catch (Exception ex) {
                    log.error(ex.toString());
                }
            }
        }

    }

    /**
     * post请求
     */
    private static Request buildRequest() {
        Request request = new Request("https://www.dfsmw.com/caiyun/");
        request.setMethod(HttpConstant.Method.POST);
        try {
            Map<String, Object> params = new HashMap<>(5);
            // 0 女 1 男
            params.put("sex", "0");
            params.put("date", String.format("%d-%d-%d-%d", 1985, 2, 10, 16));
            params.put("hour", "1");
            params.put("Init", true);
            request.setRequestBody(HttpRequestBody.form(params, "utf-8"));
        } catch (Exception ex) {
            log.error(ex.toString());
        }
        return request;
    }

    public static void main(String[] args) {
        log.info("geek start......");

        YiShengCaifuProcessor.starttime = args[0].replace("_"," ");

        Spider.create(new YiShengCaifuProcessor())
                .addPipeline(new ConsolePipeline())
                //.addPipeline(new YishengCaiyunPipeline())
                .addRequest(buildRequest())
                .setDownloader(new YishengMyDownload())
                .thread(ConfigUtil.getInstance().getThreadNum())
                .run();
        log.info("geek end......");

        cleanData();
    }


    private static void cleanData() {
        log.info("start data clean......");
        try {
            if (YishengCaiyunPipeline.synchronizedList != null && YishengCaiyunPipeline.synchronizedList.size() > 0) {
                SqlSession session = DButils.getInstance().getSqlSessionFactory().openSession();
                CaiyunCesuanService fateMapper = session.getMapper(CaiyunCesuanService.class);
                fateMapper.batchCaiyunCesuan(YishengCaiyunPipeline.synchronizedList);
                session.commit();
            }
        } catch (Exception ex) {
            log.info("data clean error......data:" + JSON.toJSONString(YishengCaiyunPipeline.synchronizedList));
        }
        log.info("data clean sucess......");
    }
}
