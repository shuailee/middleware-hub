package com.geek.calendar.spider.spilder.fate;

import com.geek.calendar.spider.plugin.FateDownload;
import com.geek.calendar.spider.plugin.FateToMysqlPipeline;
import com.geek.calendar.spider.utils.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import java.text.ParseException;

/**
 * @program: spider-demo
 * @description: FatePageProcessor
 * @author: kelin
 * @create: 2020-04-15 11:35
 **/

@Slf4j
public class FatePageProcessor extends BaseFateProcessor  {

    @Override
    public void process(Page page) {
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




    public static void main(String[] args) {
        log.info("geek start......");
        FatePageProcessor fatePageProcessor = new FatePageProcessor();
        FateToMysqlPipeline pipeline = new FateToMysqlPipeline();
        Spider.create(fatePageProcessor)
                .addPipeline(new ConsolePipeline())
                //.addPipeline(pipeline)
                .addRequest(fatePageProcessor.buildRequest())
                .setDownloader(new FateDownload())
                .thread(ConfigUtil.getInstance().getThreadNum())
                .run();

        pipeline.flush();
        log.info("geek end......");
    }

}
