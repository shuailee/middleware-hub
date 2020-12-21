package com.geek.calendar.spider.plugin;

import com.geek.calendar.spider.model.ErrorlogInfo;
import com.geek.calendar.spider.service.YscyErrorlogService;
import com.geek.calendar.spider.utils.DButils;
import com.geek.calendar.spider.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.ibatis.session.SqlSession;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.utils.HttpConstant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @program: geek-calendar-spider
 * @description: FateDownload
 * @author: shuai.li
 * @create: 2020-04-17 16:30
 **/
@Slf4j
public class YishengMyDownload extends HttpClientDownloader {

    public static final int CODE_503 = 503;

    public YishengMyDownload() {
        setProxy();
    }

    @Override
    protected void onError(Request request) {
        /**
         * 儅請求錯誤時重新獲取代理
         * */
        // setProxy();
        insertErrorLog(request, "timeout");

        super.onError(request);
    }


    @Override
    protected Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException {
        Page page = new Page();
        if (httpResponse.getStatusLine().getStatusCode() != HttpConstant.StatusCode.CODE_200) {
            if (httpResponse.getStatusLine().getStatusCode() == CODE_503) {
                // 热更新代理ip，频繁被封会导致代理ip池耗尽
                // setProxy();
                log.info("503 重新獲取ip");
            }
            insertErrorLog(request, String.valueOf(httpResponse.getStatusLine().getStatusCode()));
        } else {
            page = super.handleResponse(request, charset, httpResponse, task);
        }
        return page;
    }

    /**
     * 設置代理
     * 最好将每次获取的代理ip存储到redis，复用
     */
    private void setProxy() {
        try {
            List<Proxy> proxies = null;


/*            // 去緩存獲取ip
            String[] pools = RedisUtil.getIPPool();
            if (pools != null) {
                proxies = new ArrayList<>();
                for (String port : pools) {
                    proxies.add(new Proxy(AgentInfo.proxy_server, Integer.valueOf(port.replace(" ", "").trim())));
                }
                if (proxies != null && proxies.size() > 0) {
                    this.setProxyProvider(new SimpleProxyProvider(proxies));
                    log.info("请求代理緩存IP：:" + pools);
                }
            } else {
                //如果緩存沒有則去請求新的代理ip
                int i = 0;
                while (i < 2) {
                    proxies = AgentInfo.buildProxyIP();
                    if (CollectionUtils.isNotEmpty(proxies)) {
                        break;
                    }
                    log.info("獲取代理次數:" + i);
                    i++;
                    Thread.sleep(5000);
                }
                if (CollectionUtils.isNotEmpty(proxies)) {
                    log.info("请求代理IP：:" + proxies);
                    this.setProxyProvider(new SimpleProxyProvider(proxies));
                }
            }*/


            //如果緩存沒有則去請求新的代理ip
            int i = 0;
            while (i < 2) {
                proxies = AgentInfo.buildProxyIP();
                if (CollectionUtils.isNotEmpty(proxies)) {
                    break;
                }
                log.info("獲取代理次數:" + i);
                i++;
                Thread.sleep(5000);
            }
            if (CollectionUtils.isNotEmpty(proxies)) {
                log.info("请求代理IP：:" + proxies);
                this.setProxyProvider(new SimpleProxyProvider(proxies));
            }

        } catch (Exception e) {
            log.info("請求代理ip異常:" + e);
        }
    }

    private void insertErrorLog(Request request, String code) {
        try {
            String parameter = new String(request.getRequestBody().getBody());
            log.info("error:" + parameter);
            ErrorlogInfo errorlogInfo = new ErrorlogInfo();
            errorlogInfo.setErrorcode(code);
            errorlogInfo.setErrortype("YSCY");
            errorlogInfo.setParameter(parameter);
            errorlogInfo.setUrl(request.getUrl());
            SqlSession session = null;
            if (errorlogInfo != null) {
                try {
                    session = DButils.getInstance().getSqlSessionFactory().openSession();
                    YscyErrorlogService errorlogService = session.getMapper(YscyErrorlogService.class);
                    errorlogService.insertErrorlog(errorlogInfo);
                    session.commit();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (session != null) {
                        session.close();
                    }
                }
            }
        } catch (Exception ex) {
            log.error(ex.toString());
        }
    }


}
