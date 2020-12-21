package com.shuailee;

import com.shuailee.dao.IUserDao;
import com.shuailee.model.User;
import com.shuailee.model.UserModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class AppDemo
{

    /**
     * 1  简单查询
     * */
    public void simpleSelect() {
        /**
         *
         * *从 XML 中构建 SqlSessionFactory, 执行查询和map
         * 每个基于 MyBatis 的应用都是以一个 SqlSessionFactory 的实例为核心的。SqlSessionFactory 的实例可以通过 SqlSessionFactoryBuilder
         * 获得。而 SqlSessionFactoryBuilder 则可以从 XML 配置文件或一个预先定制的 Configuration 的实例构建出 SqlSessionFactory 的实例
         *
         *  从 XML 文件中构建 SqlSessionFactory 的实例非常简单，建议使用类路径下的资源文件进行配置
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
        //从 SqlSessionFactory 中获取 SqlSession
        //既然有了 SqlSessionFactory，顾名思义，我们就可以从中获得 SqlSession 的实例了。
        //SqlSession 完全包含了面向数据库执行 SQL 命令所需的所有方法。你可以通过 SqlSession 实例来直接执行已映射的 SQL 语句

        //为了指定创建哪种环境，只要将它作为可选的参数传递给 SqlSessionFactoryBuilder 即可，指定环境配置pro，如果忽略了环境参数，那么默认环境将会被加载
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream,"pro");
        SqlSession sqlSession= sqlSessionFactory.openSession();
        try {
            //执行查询和映射
            //使用正确描述每个语句的参数和返回值的接口
            IUserDao userMapper = sqlSession.getMapper(IUserDao.class);

            List<User> user = userMapper.queryUser();
            System.out.println(user.get(0).toString());
        }finally {
            sqlSession.close();
        }

    }

    /**
     * 2 返回类型为HashMap的查询
     * */
    public void queryUserByID(){
        SqlSession sqlSession= BuildSessionFactory.sqlSessionFactory.openSession();
        try{
            IUserDao userMapper = sqlSession.getMapper(IUserDao.class);
            //键为列名 value为值
            HashMap<String,Object> user = userMapper.queryUserByID(1);
            System.out.println("返回hashmap数量"+user.size());

        }finally {
            sqlSession.close();
        }
    }

    /**
     * 插入一条记录
     * */
    public void insertUser(){
        SqlSession sqlSession= BuildSessionFactory.sqlSessionFactory.openSession();
        try{
            IUserDao userMapper = sqlSession.getMapper(IUserDao.class);
            User user=new User("钢铁侠","男","1970-1-2","gangtiexia@gmail.com","3213215","183566564","newyork");
            int i=userMapper.insertUser(user);
            System.out.println("受影响行数"+i);

        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }
    /**
     * 插入一组记录
     * */
    public void batchInsertUser(){
        SqlSession sqlSession= BuildSessionFactory.sqlSessionFactory.openSession();
        try{
            IUserDao userMapper = sqlSession.getMapper(IUserDao.class);
            List<User> users=new ArrayList<>();

            users.add(new User("钢铁侠1","男","1970-1-2","gangtiexia@gmail.com",
                    "3213215","183566564","newyork"));
            users.add(new User("钢铁侠2","男","1970-1-2","gangtiexia@gmail.com",
                    "3213215","183566564","newyork"));

            //批量插入并返回插入主键
            userMapper.batchInsertUser(users);
            List<String> ids= users.stream().map(u->String.valueOf(u.getUser_id())).collect(Collectors.toList());
            System.out.println("新增记录的主键id："+ com.sun.deploy.util.StringUtils.join(ids,","));

        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    /**
     * 更改一条数据
     * */
    public void updatetUser(){
        SqlSession sqlSession= BuildSessionFactory.sqlSessionFactory.openSession();
        try{
            IUserDao userMapper = sqlSession.getMapper(IUserDao.class);
            User user=new User("钢铁侠","男","1970-1-2","gangtiexia@gmail.com","3213215","183566564","newyork");
            user.setUser_id(13);
            int i=userMapper.updateUser(user);
            System.out.println("受影响行数"+i);

        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    /**
     * 删除一条数据
     * */
    public void deleteUserByid()
    {
        SqlSession sqlSession= BuildSessionFactory.sqlSessionFactory.openSession();
        try{
            IUserDao userMapper = sqlSession.getMapper(IUserDao.class);
            int i=userMapper.deleteUserByID(17);
            System.out.println("受影响行数"+i);

        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }






/********************/

    /**
     * 输入类型为HashMap的查询
     * */
    public void queryUserByName(){
        SqlSession sqlSession= BuildSessionFactory.sqlSessionFactory.openSession();
        try{
            IUserDao userMapper = sqlSession.getMapper(IUserDao.class);
            Map<String,Object> parameter=new HashMap<>();
            parameter.put("sex","1");
            parameter.put("name","张%");
            //键为列名 value为值
            //List<UserModel> user = userMapper.queryUserinfo(parameter);
            List<UserModel> user = userMapper.queryUserinfo2(parameter);
            for (UserModel item:user) {
                System.out.println(item.toString());
            }

        }finally {
            sqlSession.close();
        }
    }


    /**
     * 更改
     * */
    public void updatetUserByid(){
        SqlSession sqlSession= BuildSessionFactory.sqlSessionFactory.openSession();
        try{
            IUserDao userMapper = sqlSession.getMapper(IUserDao.class);
            UserModel user=new UserModel("蜘蛛侠","猪","1970-1-2","gangtiexia@gmail.com","3213215","183566564","newyork");
            user.setUserId(13);
            int i=userMapper.updateUserById3(user);
            System.out.println("受影响行数"+i);

        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }


    /**
     * 输入类型为 list
     * */
    public void queryUserByids(){
        SqlSession sqlSession= BuildSessionFactory.sqlSessionFactory.openSession();
        try{
            IUserDao userMapper = sqlSession.getMapper(IUserDao.class);
            List<String> ids=new ArrayList<>();
            ids.add("13");
            ids.add("15");
            List<User> user = userMapper.queryUserbyids(ids);
            for (User item:user) {
                System.out.println(item.toString());
            }

        }finally {
            sqlSession.close();
        }
    }
    public static void main( String[] args )
    {
        AppDemo appDemo =new AppDemo();
        //appDemo.deleteUserByid();
        //appDemo.batchInsertUser();
        appDemo.queryUserByID();

    }
}
