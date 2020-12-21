package com.shuailee.study.jdbcdemo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * JDBC 访问mysql数据库存储过程练习
 */
public class JdbcPocedure {
    public static void main(String[] args) {
        DBHelper db = DBHelper.getInstance();
        Connection conn = db.openConection();
        System.out.println(conn);
        //调用存储过程插入一条数据
        System.out.println("使用CallableStatement命令调用存储过程执行插入");
        People p = new People();
        p.setAge(110);
        p.setSysno(11);
        p.setUsername("桥");
        InserPeopleByProcedureAndCallableStatement(conn, p);
        QueryAgeByProcedureAndCallableStatement(conn, "桥");
        //查询符合条件的人员信息
        System.out.println("使用PreparedStatement命令面向对象查询结果");
        List<People> ps = getPeople(conn, 1003, "kitty");
        if (ps != null) {
            for (People m : ps) {
                System.out.println("编号:" + m.getSysno() + "  username:" + m.getUsername());
            }
        }
    }

    /**
     * 使用PreparedStatement查询符合命令的人员列表
     * PreparedStatement 动态的操作sql（带参数），可以防止注入
     * Statement 静态的操作sql（不带参数）
     */
    @SuppressWarnings("null")
    public static List<People> getPeople(Connection conn, int sysno, String username) {
        String sql = "select sysno,username,age from tuser where sysno = ? or  username= ? ";
        try {

            //Statement stmt=conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sysno);
            pstmt.setString(2, username);
            ResultSet rs = pstmt.executeQuery();
            List<People> peoples = new ArrayList<People>();
            ;
            while (rs.next()) {
                int s = rs.getInt("sysno");
                String un = rs.getString("username");
                int age = rs.getInt("age");

                People p1 = new People();
                p1.setSysno(s);
                p1.setUsername(un);
                p1.setAge(age);
                peoples.add(p1);
            }
            return peoples;
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 使用存储过程和CallableStatement命令插入一条数据
     */
    public static void InserPeopleByProcedureAndCallableStatement(Connection conn, People p) {
        /**
         * 存储过程内容,输入参数默认为 in  输出为 out：
         * create procedure pro_insertpeople_tuser(in sysno int,in uname varchar(20),age int )
         * insert into tuser (sysno,username,age) values(sysno,uname,age)
         * mysql中调用： call pro_insertpeople_tuser(10,'shuai',26)
         * */

        String procedureSql = " call pro_insertpeople_tuser(?,?,?) "; //存储过程调用语句
        try {
            CallableStatement cstmt = conn.prepareCall(procedureSql);
            cstmt.setInt(1, p.getSysno());
            cstmt.setString(2, p.getUsername());
            cstmt.setInt(3, p.getAge());
            int i = cstmt.executeUpdate();
            System.out.println("受影响行数:" + i);

        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }


    /**
     * 使用存储过程和CallableStatement命令根据username查询年龄
     */
    public static void QueryAgeByProcedureAndCallableStatement(Connection conn, String username) {
        /**
         * 存储过程内容,输入参数默认为 in  输出为 out：
         * CREATE PROCEDURE `pro_querypeo_tuser`(in uname varchar(20), out outage int)
         BEGIN
         select age into outage from tuser where username = uname ;
         END
         * mysql中调用：set @outage = 0;
         call test.pro_querypeo_tuser('kitty', @outage);
         select @outage;
         * */

        String procedureSql = " call pro_querypeo_tuser(?,?) "; //存储过程调用语句
        try {
            CallableStatement cstmt = conn.prepareCall(procedureSql);
            cstmt.setString(1, username);
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.execute();
            //获得第2个输出参数的值
            int age = cstmt.getInt(2);
            System.out.println("年龄:" + age);

        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

}


/**
 * DAO
 */
class People {
    private int sysno;
    private String username;
    private int age;

    public int getSysno() {
        return sysno;
    }

    public void setSysno(int sysno) {
        this.sysno = sysno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
