package com.geek.calendar.spider.spilder.fate;

import com.alibaba.fastjson.JSON;
import com.geek.calendar.spider.model.FileDataInfo;
import com.geek.calendar.spider.model.FateConst;
import com.geek.calendar.spider.plugin.FateDownload;
import com.geek.calendar.spider.utils.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.utils.HttpConstant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: spider-demo
 * @description: FailDataProcessor
 * @author: kelin
 * @create: 2020-04-22 15:10
 **/
@Slf4j
public class FailDataProcessor extends BaseFateProcessor {

    @Override
    public void process(Page page) {
        String parameter = new String(page.getRequest().getRequestBody().getBody());
        log.info("请求参数:" + parameter);
        if (parameter.indexOf("Init") > 0) {
            try {
                this.addTargetRequestList(page);
                log.info("请求次数:" + (page.getTargetRequests() != null ? page.getTargetRequests().size() : 0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            super.pageAnalysis(page);
        }

    }

    @Override
    protected void addTargetRequestList(Page page)  {
        List<FileDataInfo> fileDataInfoList = getFailData();
        for (FileDataInfo data : fileDataInfoList) {
            Request request = new Request("http://www.99166.com/zt/2020/result.asp");
            request.setMethod(HttpConstant.Method.POST);
            Map<String, Object> params = new HashMap<>(5);
            params.put(FateConst.PARAMETER_SEX, data.getSex().equalsIgnoreCase("M") ? "male" : "female");
            params.put(FateConst.PARAMETER_YEAR, data.getYear());
            params.put(FateConst.PARAMETER_MONTH, data.getMonth());
            params.put(FateConst.PARAMETER_DAY, data.getDay());
            params.put(FateConst.PARAMETER_HOUR, data.getHour());
            params.put("Tablename", "buchang");
            request.setRequestBody(HttpRequestBody.form(params, "utf-8"));
            page.addTargetRequest(request);
        }

    }


    private List<FileDataInfo> getFailData() {
        // String path = "D:\\log\\faildata\\194.log";
        String path = "/root/yunshi/data/data.log";
        File file = new File(path);
        List<FileDataInfo> fileDataInfoList = new ArrayList<>();
        try {
            //构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line = null;
            //使用readLine方法，一次读一行
            while ((line = br.readLine()) != null) {
                if (line.indexOf("[INFO]") < 0) {
                    continue;
                } else {
                    line = line.replace("[INFO]", "");
                    System.out.println(line);
                    FileDataInfo fileDataInfo = JSON.parseObject(line, FileDataInfo.class);
                    fileDataInfoList.add(fileDataInfo);
                }
            }
            br.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return fileDataInfoList;
    }



    public static void main(String[] args) {
        log.info("geek start......");
        FailDataProcessor failDataProcessor=new FailDataProcessor();
        Spider.create(failDataProcessor)
                .addPipeline(new ConsolePipeline())
                //.addPipeline(new FateToMysqlPipeline())
                .addRequest(failDataProcessor.buildRequest())
                .setDownloader(new FateDownload())
                .thread(ConfigUtil.getInstance().getThreadNum())
                .run();
        log.info("geek end......");

    }

}
