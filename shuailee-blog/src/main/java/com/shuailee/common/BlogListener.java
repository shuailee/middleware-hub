package com.shuailee.common;

import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: shuailee-blog
 * @description:
 * @author: shuai.li
 * @create: 2018-12-17 12:51
 **/
@WebListener
public class BlogListener implements ServletContextListener {

    /**
     * 当Servlet 容器启动Web 应用时调用该方法。在调用完该方法之后，容器再对Filter 初始化，
     * 并且对那些在Web 应用启动时就需要被初始化的Servlet 进行初始化。
     * */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        //servlet容器在实例化ConfigListener并调用其方法之前，要确保spring容器已经初始化完毕
        WebApplicationContextUtils.getRequiredWebApplicationContext(servletContextEvent.getServletContext())
                .getAutowireCapableBeanFactory().autowireBean(this);


        //确保指定的bean初始化完毕
       /* ConfigService configService = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()).getBean(ConfigService.class);
        configService.initConfig();*/

        /*
        * 例一：在服务启动时，将数据库中的数据加载进内存，并将其赋值给一个属性名，其它的 Servlet 就可以通过 getAttribute 进行属性值的访问。
        * */
        //ServletContext 对象是一个为整个 web 应用提供共享的内存，任何请求都可以访问里面的内容
        ServletContext sct=servletContextEvent.getServletContext();
        Map<Integer,String> depts=new HashMap<Integer,String>();
        depts.put(1,"hello world");
        sct.setAttribute("dept", depts);
        System.out.println("======自定义监听器：listener test is beginning=========");
        //在完成上述编码后，仍需在 web.xml 中进行如下配置，以使得该监听器可以起作用。,spring boot 只需加入WebListener注解即可
    }
    /**
     * 当Servlet 容器终止Web 应用时调用该方法。在调用该方法之前，容器会先销毁所有的Servlet 和Filter 过滤器。
     * */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("======自定义监听器：this is last destroyeed======");
    }
}
