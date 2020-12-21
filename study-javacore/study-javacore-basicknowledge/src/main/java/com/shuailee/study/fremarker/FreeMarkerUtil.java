package com.shuailee.study.fremarker;



import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: flight-job
 * @description:
 * @author: shuai.li
 * @create: 2018-07-26 16:30
 **/
public class FreeMarkerUtil {

    public static void main(String [] args)
    {
        try {
            getEmailContent("");
        }catch (Exception ex){

        }
    }


    public static String getEmailContent(String name) throws IOException, TemplateException {

/*
        //获取项目路径
        URL url = TestFuture.class.getResource("/");
        System.out.println(url.getPath());
        String path = System.getProperty("user.dir");
        System.out.println(path);*/

        Configuration conf = new Configuration(Configuration.VERSION_2_3_21);
        conf.setDefaultEncoding("UTF-8");
        conf.setClassForTemplateLoading(FreeMarkerUtil.class, "/templates");
        Template template = conf.getTemplate("emailmonitor.ftl");
        //Writer out = new OutputStreamWriter(System.out);
        StringWriter out = new StringWriter();
        template.process(getData(), out);
        String s= out.toString();
        System.out.println(s);
        return  s;

    }


    private static Map<String,Object> getData()
    {
        Map<String,Object> root = new HashMap<String,Object>();
        /*List<ReissueEmailDto> reissueEmailDto=new ArrayList<>();
        ReissueEmailDto po=new ReissueEmailDto();
        po.setTemplate(EmailTemplateDTO.NewFlightPaymentSuccess);*/
        //reissueEmailDto.add(po);
        //root.put("ReissueEmails",reissueEmailDto);
        return root;
    }
}
