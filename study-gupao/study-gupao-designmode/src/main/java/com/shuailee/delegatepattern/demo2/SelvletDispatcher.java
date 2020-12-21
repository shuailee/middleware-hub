package com.shuailee.delegatepattern.demo2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: study-gupao
 * @description: 结合了策略模式、工厂模式、单例模式 实现将请求映射到对应的controller上处理
 *
 * 以delegate或者Dispatcher结尾的大部分是委托
 * @author: shuai.li
 * @create: 2019-05-13 19:42
 **/
public class SelvletDispatcher {


    private Map<String,Handler> handlerMapping=new HashMap<>();

    public SelvletDispatcher(){
        try {
            Class clazz = MemberAction.class;
            handlerMapping.put("/web/getMemberById", new Handler()
                    .setController(clazz.newInstance())
                    .setMethod(clazz.getMethod("getMemberById", new Class[]{String.class}))
                    .setUrl("/web/getMemberById"));

        }catch (Exception ex){
        }
    }

    private void  doService(HttpServletRequest request, HttpServletResponse response){
        doDispatch(request,response);
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response){
        //1.获取用户请求的url
        // 如果按照 J2EE 的标准、每个 url 对对应一个 Serlvet，url 由浏览器输入
        String uri =   request.getRequestURI();

        //2、根据uri 去handlerMapping找到对应的hanler
        //Servlet 拿到 url 以后，要做权衡（要做判断，要做选择）
        // 根据用户请求的 URL，去找到这个 url 对应的某一个 java 类的方法
        Handler handler =handlerMapping.get(uri);
        try {
            //3.将具体的任务分发给Method（通过反射去调用其对应的方法）
            Object obj = handler.getMethod().invoke(handler.getController(),request.getParameter("mid"));
            //4、获取到Method执行的结果，通过Response返回出去
            //response.getWriter().write();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}