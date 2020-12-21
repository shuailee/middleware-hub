package com.shuailee.study.webmagicdemo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class HupuSpilder implements PageProcessor {
    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public Site getSite() {
        return site;
    }
    @Override
    public void process(Page page) {
        // 匹配某一篇文章
       // page.putField("title",page.getHtml().xpath("/html/body/div[4]/div[1]/div[1]/h1/text()").toString());

        // 可以通过浏览器拷贝xpath
        // 文章页，匹配 https://voice.hupu.com/nba/七位数字.html  正则是文章页的url
        if (page.getUrl().regex("https://voice\\.hupu\\.com/nba/[0-9]{7}\\.html").match()) {
            page.putField("Title", page.getHtml().xpath("/html/body/div[4]/div[1]/div[1]/h1/text()").toString());
            page.putField("Content",
                    page.getHtml().xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/p/text()").all().toString());
        }
        else {
            // 爬取所有列表页的url，并将其添加到后续爬取队列
            // 文章url
            page.addTargetRequests(
                    page.getHtml().xpath("/html/body/div[3]/div[1]/div[2]/ul/li/div[1]/h4/a/@href").all());
            // 翻页url
            page.addTargetRequests(
                    page.getHtml().xpath("/html/body/div[3]/div[1]/div[3]/a[@class='page-btn-prev']/@href").all());
        }

        
    }

    public static void main(String[] args) {
        Spider.create(new HupuSpilder())
                //要爬取的初始url
                .addUrl("https://voice.hupu.com/nba/1")
                //为爬取到的数据指定处理方式：打印到控制台还是记录到本地日志，还是记录到数据库（.addPipeline(new MysqlPipeline())）
                //.addPipeline(new ConsolePipeline())
                .addPipeline(new MysqlPipeline())
                //开启三个线程爬取
                .thread(1)
                .run();

    }
}
