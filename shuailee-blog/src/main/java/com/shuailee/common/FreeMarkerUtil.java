package com.shuailee.common;

import com.shuailee.model.Article;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FreeMarkerUtil {


    /*
    * 一般使用FreeMarker来构建邮件模板，然后发送一段html代码给收件人
    * */
   public String getMailContent() {
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        StringWriter out=new StringWriter();
        try {
            // step2 获取模版路径
            configuration.setClassForTemplateLoading(this.getClass(), "/templates");
            //step3 加载模版文件
            Template template = configuration.getTemplate("articles.ftl");
            // step4 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<Article> articles = new ArrayList<>();
            Article a1=  new Article();
            a1.setArticle_title("shuai");
            articles.add(a1);
            dataMap.put("articles",articles);
            StringWriter stringWriter=new StringWriter();
            // 模板和数据渲染
            template.process(dataMap, out);

          /*//*
            //写入文件
            File docFile = new File(CLASS_PATH + "\\" + "AutoCodeDemo.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            //输出文件
            template.process(dataMap, out);
            */

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return out.toString();
    }
}
