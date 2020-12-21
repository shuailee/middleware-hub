package com.shuailee;

import com.shuailee.dao.IUserDao;
import com.shuailee.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-20 14:51
 **/
public class BuildSessionFactory {
    public static SqlSessionFactory sqlSessionFactory;

    /**
    * 静态代码块就是给类初始化的,它是随着类的加载而执行，只执行一次，并优先于主函数
    * 构造函数是初始化对象的
    * 一个类中可以有多个静态代码块
    * 执行顺序分别为： 1、静态代码块 2、main 函数  3、构造代码块 4、构造函数
    * */
    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream, "pro");
    }
}
