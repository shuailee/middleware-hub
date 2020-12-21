package com.shuailee.study.webmagicdemo;

import com.shuailee.study.webmagicdemo.model.News;
import com.shuailee.study.webmagicdemo.services.INews;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

// 自定义实现Pipeline接口
public class MysqlPipeline implements Pipeline {

    public MysqlPipeline() {
    }
    @Override
    public void process(ResultItems resultitems, Task task) {
        Map<String, Object> mapResults = resultitems.getAll();
        Iterator<Map.Entry<String, Object>> iter = mapResults.entrySet().iterator();
        Map.Entry<String, Object> entry;
        // 输出到控制台
        while (iter.hasNext()) {
            entry = iter.next();
            System.out.println(entry.getKey() + "：" + entry.getValue());
        }
        // 持久化
        News news = new News();
        if (!mapResults.get("Title").equals("")) {
            news.setTitle((String) mapResults.get("Title"));
            news.setContent((String) mapResults.get("Content"));
        }
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            //为了指定创建哪种环境，只要将它作为可选的参数传递给 SqlSessionFactoryBuilder 即可，指定环境配置pro，如果忽略了环境参数，那么默认环境将会被加载
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is,"pro");
            SqlSession session = sessionFactory.openSession();
           /* session.insert("add", news);
           */

            INews userMapper = session.getMapper(INews.class);
            userMapper.insertNews(news);

            session.commit();
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}