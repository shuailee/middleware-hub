package com.shuailee.template.servicehandler;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-05-27 21:15
 **/
public abstract class AbstractHandler {

    //初始化接口名
    //读取配置中心，获取调用配置，设置调用环境（是否启用缓存，mars请求响应拦截用于mock）

    //请求耗时统计开始
    //发起接口调用
    //请求耗时结束，手机耗时日志


    //收集catch日志，打印错误
    //收集 序列化请求响应对象字符串

    //异步记录日志，持久化报文

    //收集的信息记录到当前线程的上下文容器中（单例）



    abstract void mapRequest();
    abstract void invoke();
    abstract void mapResponse();

    public final void process(){
        mapRequest();
        invoke();
        mapResponse();
    }



}
