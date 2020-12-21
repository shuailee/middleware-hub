package com.shuailee;

import com.shuailee.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-27 19:22
 **/
public class JdbcDemo {
    public static void main(String[] args) {
        queryUser();
    }

    public static final  String DRIVER= "com.mysql.jdbc.Driver";
    public static final  String DB_URL= "jdbc:mysql://localhost:3306/shuai";
    public static final  String USER= "root";
    public static final  String PASSWORD= "1qaz@WSX";

    public static void queryUser() {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            // 1 注册JDBC 驱动
            Class.forName(DRIVER);
            // 2 打开连接
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            // 3 们通过Connection 创建一个Statement 对象。
            stmt = conn.createStatement();
            // 4 通过Statement 的execute()方法执行SQL。当然Statement 上面定义了非常多的方法。execute()方法返回一个ResultSet 对象，
            // 我们把它叫做结果集
            String sql="SELECT * FROM tb_user";
             rs= stmt.executeQuery(sql);
            // 5 我们通过ResultSet 获取数据。转换成一个POJO 对象。
            List<User> users=new ArrayList<>();
            while(rs.next()){

                //可以根据标识读取，也可以根据列索引
                int id=rs.getInt("user_id");
                String username=rs.getString("user_name");
                String user_address=rs.getString("user_address");

                User user=new User();
                user.setUser_id(id);
                user.setUser_name(username);
                user.setUser_address(user_address);
                users.add(user);
            }
            System.out.println("result size:"+users.size());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //6  最后，我们要关闭数据库相关的资源，包括ResultSet、Statement、Connection，它们的关闭顺序和打开的顺序正好是相反的
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
