package com.geek.calendar.spider.plugin;

import com.geek.calendar.spider.model.ErrorlogInfo;
import com.geek.calendar.spider.service.ErrorlogService;
import com.geek.calendar.spider.utils.DButils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.ibatis.session.SqlSession;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.utils.HttpConstant;

import java.io.IOException;


/**
 * @program: spider-demo
 * @description: FateDownload
 * @author: kelin
 * @create: 2020-04-17 16:30
 **/
@Slf4j
public class FateDownload extends HttpClientDownloader {


    @Override
    protected void onError(Request request) {

        insertErrorInfo(request, "RequestFail");
        //updatelog(request);
        super.onError(request);
    }

    @Override
    protected Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException {
        Page page = new Page();
        if (httpResponse.getStatusLine().getStatusCode() != HttpConstant.StatusCode.CODE_200) {
            // 实现失败重试需要配合 Spider 的实现类里 site = Site.me().setCycleRetryTimes(3000);
            page.setDownloadSuccess(false);
            insertErrorInfo(request, String.valueOf(httpResponse.getStatusLine().getStatusCode()));
            //updatelog(request);

        } else {
            page = super.handleResponse(request, charset, httpResponse, task);
        }
        return page;
    }

    /**
     * 修改日志狀態
     */
    private void updatelog(Request request) {
        try {
            String parameter = new String(request.getRequestBody().getBody());
            if (parameter.indexOf("%") > -1) {
                // 视参数拼接情况而定
                // StrYear1=1934&StrMonth=2&StrDay=10&StrTime=13&StrSex=male&Tablename=buchang
                parameter = parameter.substring(0, parameter.lastIndexOf('&'));
            }
            SqlSession session = null;
            try {
                session = DButils.getInstance().getSqlSessionFactory().openSession();
                ErrorlogService errorlogService = session.getMapper(ErrorlogService.class);
                errorlogService.updateLogStatus(parameter);
                session.commit();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        } catch (Exception ex) {
            log.error(ex.toString());
        }
    }


    private void insertErrorInfo(Request request, String code) {
        try {
            String parameter = new String(request.getRequestBody().getBody());
            log.info("error:" + parameter);
            ErrorlogInfo errorlogInfo = new ErrorlogInfo();

            errorlogInfo.setErrorcode(code);
            errorlogInfo.setErrortype(code);
            errorlogInfo.setParameter(parameter);
            errorlogInfo.setUrl(request.getUrl());
            SqlSession session = null;
            if (errorlogInfo != null) {
                try {
                    session = DButils.getInstance().getSqlSessionFactory().openSession();
                    ErrorlogService errorlogService = session.getMapper(ErrorlogService.class);
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
