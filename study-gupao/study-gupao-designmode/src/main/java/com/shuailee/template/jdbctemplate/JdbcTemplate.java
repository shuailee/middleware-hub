package com.shuailee.template.jdbctemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-05-30 19:20
 **/
public abstract class JdbcTemplate {

    /*
    *
    创建一个模板类JdbcTemplate，封装所有的JDBC 操作。以查询为例，每次查询的表不同，返回的数据结构也就不一样。我们针对不同的数据，
    都要封装成不同的实体对象。而每个实体封装的逻辑都是不一样的，但封装前和封装后的处理流程是不变的，因此，我们可以使用模板方法模式来设计这样的业务场景
    * */


    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取链接
     */
    public Connection getConnection() throws Exception {
        return this.dataSource.getConnection();
    }

    /**
     * 创建语句集
     */
    protected PreparedStatement createPrepareStatement(Connection conn, String sql) throws Exception {
        return conn.prepareStatement(sql);
    }

    /**
     * 对象转换
     */
    protected List<?> paresResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<Object>();
        int rowNum = 1;
        while (rs.next()) {
            result.add(rowMapper.mapRow(rs, rowNum++));
        }
        return result;
    }

    /**
     * 执行查询
     */
    protected ResultSet executeQuery(PreparedStatement pstm, Object[] values) throws Exception {
        for (int i = 0; i < values.length; i++) {
            pstm.setObject(i, values[i]);
        }
        return pstm.executeQuery();
    }

    /**
     * 关闭数据库链接
     */
    public void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }

    /**
     * 关闭命令集
     */
    protected void closeStatement(PreparedStatement pstm) throws Exception {
        pstm.close();
    }

    /**
     * 关闭结果集
     */
    protected void closeResultSet(ResultSet rs) throws Exception {
        rs.close();
    }

    public List<?> executeQuery(String sql, RowMapper<?> rowMapper, Object[] values) {
        try {
            //1、获取连接
            Connection conn = this.getConnection();
            //2、创建语句集
            PreparedStatement pstm = this.createPrepareStatement(conn, sql);
            //3、执行语句集
            ResultSet rs = this.executeQuery(pstm, values);
            //4、处理结果集
            List<?> result = this.paresResultSet(rs, rowMapper);
            //5、关闭结果集
            this.closeResultSet(rs);
            //6、关闭语句集
            this.closeStatement(pstm);
            //7、关闭连接
            this.closeConnection(conn);
            return result;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
