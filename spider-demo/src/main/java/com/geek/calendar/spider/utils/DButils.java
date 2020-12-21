package com.geek.calendar.spider.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: geek-calendar-spider
 * @description: DButils
 * @author: shuai.li
 * @create: 2020-04-16 14:22
 **/
public class DButils {
    private volatile static DButils lazy = null;
    private DButils(){}
    public static DButils getInstance(){
        if(lazy == null){
            synchronized (DButils.class){
                if(lazy == null){
                    lazy = new DButils();
                }
            }
        }
        return lazy;
    }


    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is, "dev");
        return sessionFactory;
    }

}
