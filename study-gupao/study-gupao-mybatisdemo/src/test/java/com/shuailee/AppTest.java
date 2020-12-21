package com.shuailee;

import static org.junit.Assert.assertTrue;

import com.shuailee.dao.IUserDao;
import com.shuailee.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Unit test for simple AppDemo.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    private SqlSession sqlSession;

    /**
     * 从 XML 中构建 SqlSessionFactory
     * */
    @Before
    public void prepareSqlSession() {

        /**
         * 每个基于 MyBatis 的应用都是以一个 SqlSessionFactory 的实例为核心的。SqlSessionFactory 的实例可以通过 SqlSessionFactoryBuilder
         * 获得。而 SqlSessionFactoryBuilder 则可以从 XML 配置文件或一个预先定制的 Configuration 的实例构建出 SqlSessionFactory 的实例
         *
         * 从 XML 文件中构建 SqlSessionFactory 的实例非常简单，建议使用类路径下的资源文件进行配置
         * 但是也可以使用任意的输入流（InputStream）实例，包括字符串形式的文件路径或者 file:// 的 URL 形式的文件路径来配置
         * MyBatis 包含一个名叫 Resources 的工具类，它包含一些实用方法，可使从 classpath 或其他位置加载资源文件更加容易
         */
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream,"pro");
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void queryUserTest() {
        IUserDao userMapper = sqlSession.getMapper(IUserDao.class);
        List<User> user1 = userMapper.queryUser();
        List<User> user2 = userMapper.queryUser();
        System.out.println(user1 == user2);
        /*System.out.println(user.get(0).toString());
        Assert.assertNotNull(user);*/
    }
}
