package com.shuailee.study.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    private DBHelper() {
    }
    private volatile static DBHelper dbHelper = null;
    private static Object object = new Object();
    public static DBHelper getInstance() {
        if (dbHelper == null) {
            synchronized (object) {
                if (dbHelper == null) {
                    dbHelper = new DBHelper();
                }
            }
        }
        return dbHelper;
    }

    /**
     * 打开数据库连接
     */
    public Connection openConection() {
        try {
            Properties prop = new Properties();
            //加载指定的属性配置文件
            prop.load(this.getClass().getClassLoader().getResourceAsStream("DBConfig.properties"));
            //读取属性配置文件的节点值
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("src");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");

            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库链接
     */
    public void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}
