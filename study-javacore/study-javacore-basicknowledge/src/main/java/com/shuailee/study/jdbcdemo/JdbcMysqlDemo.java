package com.shuailee.study.jdbcdemo;


import com.shuailee.study.model.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: study-javacore
 * @description:
 * @create: 2019-10-10 19:49
 **/
public class JdbcMysqlDemo {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    /**
     * 后缀参数不加时区会报错
     */
    public static final String DB_URL = "jdbc:mysql://192.168.93.121:3301/shuai?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "111!";

    /**
     * Statement对象和ResultSet对象 查询数据
     */
    @Test
    public void queryAll() {
        Connection conn = getConnByDatasource();
        String sql = "select user_id,user_name,user_address from tb_user";
        try {
            //获取一个sql语句执行对象Statement
            //ResultSet结果集也是可更新的，可以在查询返回结果集以后执行特定操作,它需要在创建Statement命令时指定额外参数
            //Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Statement stmt = conn.createStatement();

            //使用sql语句执行对象Statement来执行sql 查询命令，并返回一个ResultSet对象
            ResultSet rs = stmt.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                //可以根据标识读取，也可以根据列索引
                int user_id = rs.getInt("user_id");
                String user_name = rs.getString("user_name");
                String user_adress = rs.getString("user_address");
                users.add(new User(user_id, user_name, user_adress));
            }
            //System.out.println(users.toString());
            users.forEach(s -> System.out.println(s.toString()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn);
        }
    }

    /**
     * 1获取test表中的数据，并在结果集基础上加入/修改一行数据，提交到数据库
     * 2练习ResultSet对象 操作数据库的命令
     */
    @Test
    public void queryAndUpdate() {
        Connection conn = getConnByDatasource();
        String sql = "select * from tb_user";
        try {
            //获取一个sql语句执行对象Statement
            //ResultSet结果集也是可更新的，可以在查询返回结果集以后执行特定操作,它需要在创建Statement命令时指定额外参数
            //并且要求数据表有主键列id，否则会报错
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            //使用sql语句执行对象Statement来执行sql 查询命令，并返回一个ResultSet对象
            ResultSet rs = stmt.executeQuery(sql);

            //新增一行 ,并提交到数据库
            rs.moveToInsertRow(); //光标移动到新插入行的地方
            rs.updateInt("user_id", 15);
            rs.updateString("user_name", "牛栏山");
            rs.updateString("user_address", "天方夜谭");
            rs.insertRow();//添加一行

            //更新一行 并提交到数据库
            //rs.absolute(2)//定位到第2行
            //rs.updateString("username", "face"); //第2列 设置为face
            //rs.updateRow();//可以更新一行

            System.out.println("新的查询结果：");
            while (rs.next()) {
                int sysno = rs.getInt("user_id");
                String username = rs.getString("user_name");
                System.out.println("user_id:" + sysno + ",user_name：" + username);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn);
        }

    }


    /**
     * 事物 如果有一个执行失败，则回归
     *
     * A:原子性(Atomicity)      * 事务是数据库的逻辑工作单位，事务中包括的诸操作要么全做，要么全不做。
     * B:一致性(Consistency)    * 事务执行的结果必须是使数据库从一个一致性状态变到另一个一致性状态。一致性与原子性是密切相关的。
     * C:隔离性(Isolation)      * 一个事务的执行不能被其他事务干扰。
     * D:持续性/永久性(Durability)     * 一个事务一旦提交，它对数据库中数据的改变就应该是永久性的。

     数据库事务的隔离级别：

     SQL标准定义了4类隔离级别，包括了一些具体规则，用来限定事务内外的哪些改变是可见的，哪些是不可见的。低级别的隔离级一般支持更高的并发处理，并拥有更低的系统开销。

     Read Uncommitted（读取未提交内容）：在该隔离级别，所有事务都可以看到其他未提交事务的执行结果。本隔离级别很少用于实际应用，因为它的性能也不比其他级别好多少。读取未提交的数据，也被称之为脏读（Dirty Read）。
     Read Committed（读取提交内容）：这是大多数数据库系统的默认隔离级别（但不是MySQL默认的）。它满足了隔离的简单定义：一个事务只能看见已经提交事务所做的改变。这种隔离级别 也支持所谓的不可重复读（Nonrepeatable Read），因为同一事务的其他实例在该实例处理其间可能会有新的commit，所以同一select可能返回不同结果。
     Repeatable Read（可重读）：这是MySQL的默认事务隔离级别，它确保同一事务的多个实例在并发读取数据时，会看到同样的数据行。不过理论上，这会导致另一个棘手的问题：幻读 （Phantom Read）。简单的说，幻读指当用户读取某一范围的数据行时，另一个事务又在该范围内插入了新行，当用户再读取该范围的数据行时，会发现有新的“幻影” 行。InnoDB和Falcon存储引擎通过多版本并发控制（MVCC，Multiversion Concurrency Control）机制解决了该问题。
     Serializable（可串行化） ：这是最高的隔离级别，它通过强制事务排序，使之不可能相互冲突，从而解决幻读问题。简言之，它是在每个读的数据行上加上共享锁。在这个级别，可能导致大量的超时现象和锁竞争

     这四种隔离级别采取不同的锁类型来实现，若读取的是同一个数据的话，就容易发生问题。例如：

     脏读(Drity Read)：某个事务已更新一份数据，另一个事务在此时读取了同一份数据，由于某些原因，前一个RollBack了操作，则后一个事务所读取的数据就会是不正确的
     不可重复读(Non-repeatable read):在一个事务的两次查询之中数据不一致，这可能是两次查询过程中间插入了一个事务更新的原有的数据。
     幻读(Phantom Read):在一个事务的两次查询中数据笔数不一致，例如有一个事务查询了几列(Row)数据，而另一个事务却在此时插入了新的几列数据，先前的事务在接下来的查询中，就会发现有几列数据是它先前所没有的。
     *
     */
    @Test
    public void updateName() {

        Connection conn = getConnByDatasource();
        /**
         * 修改jack 为tom
         * 修改tom4 为peter
         * */
        String sql1 = "update tb_user set user_name='tom' where user_name='牛栏山'";
        //这是一条错误的语句
        String sql2 = "update tb_user settttt username='peter' where user_name='牛栏山'";
        try {
            //事物第1步：设置事物是自动提交还是手动提交，默认为true 自动提交，false 为非自动提交
            conn.setAutoCommit(false);
            //设置数据库隔离级别 mysql默认就是REPEATABLE_READ 可重读 它确保同一事务的多个实例在并发读取数据时，会看到同样的数据行
            //也可以不设置
            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            Statement stmt = conn.createStatement();
            stmt.executeLargeUpdate(sql1);
            //这是一条错误的语句
            stmt.executeLargeUpdate(sql2);
            //事物第2步:提交事物
            conn.commit();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();

            //事物第3步：抛出异常的时候，执行事物回滚
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO 自动生成的 catch 块
                e1.printStackTrace();
            }

        } finally {
            closeConn(conn);
        }

        new JdbcMysqlDemo().queryAll();


    }


    /**
     * 添加一个用户，练习 Statement 执行更新sql命令的使用
     */
    @Test
    public void addUser() {
        Connection conn = getConnByDatasource();
        int maxid = getMaxIdTest(conn);
        if (maxid != -1) {
            maxid++;
            String sql = "insert into tb_user(user_id,user_name) values(" + maxid + ",'tom" + maxid + "')";
            try {
                Statement stmt = conn.createStatement();
                //执行sql 修改命令，并返回受影响条数，也可以使用execute命令创建表等
                int i = stmt.executeUpdate(sql);
                System.out.println("受影响行数：" + i);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        new JdbcMysqlDemo().queryAll();
        closeConn(conn);
    }

    /**
     * 获取最大编号，练习 Statement 执行查询sql命令的使用
     */
    public int getMaxIdTest(Connection conn) {
        int id = -1;
        String sql = "select max(user_id) from tb_user";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //下面三者之间执行的结果是一样的

            //1 将指针定位到下一个元素，从0开始
            if (rs.next()) {
                id = rs.getInt(1);
            }
            //2 直接将指针定位到指定行
            if (rs.absolute(1)) {
                id = rs.getInt(1);
            }
            //3 将指针定位到第一行
            if (rs.first()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 1获取数据库元数据的练习（数据库版本号，名称等）
     * 2获取Resultset结果集元数据的练习(获取结果集有多少列，每列的名称等)
     */
    @Test
    public void getMetaData() {
        Connection conn = getConnByDriverManager();
        try {
            //获取数据库元数据
            DatabaseMetaData metaData = conn.getMetaData();
            int version = metaData.getDatabaseMajorVersion();
            String dbname = metaData.getDatabaseProductName();
            System.out.println("version：" + version);
            System.out.println("数据库名称：" + dbname);

            String sql = "select * from tb_user";
            Statement statement = conn.createStatement();
            //获取查询结果集元数据
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            System.out.println("数据表总列数：" + columnCount);
            for (int i = 1; i < rsMetaData.getColumnCount(); i++) {
                System.out.println("列名：" + rsMetaData.getColumnName(i));
            }

        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            closeConn(conn);
        }
    }


    /**
     * 使用DriverManager获取链接
     */
    public static Connection getConnByDriverManager() {
        try {
            //jdbc4之前加载注册驱动类的方式，在执行forname动态类加载进jvm时会执行com.mysql.jdbc.Driver服务实现类的静态代码块
            //该com.mysql.jdbc.Driver类的静态代码块会将其注册进DriverManager中，源码中静态代码块是通过调用loadInitialDrivers()方法来完成引入和查找数据库驱动的
            //如果是spi 则使用 ServiceLoader.load(Driver.class); 的方式扫描加载
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("数据库链接：" + conn);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用dpcp连接池获取链接
     */
    public static Connection getConnByDatasource() {
        try {
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName(DRIVER);
            ds.setUrl(DB_URL);
            ds.setUsername(USER);
            ds.setPassword(PASSWORD);
            //设置连接池的初始连接数
            ds.setInitialSize(5);
            //设置连接池中最少有两个空闲的链接
            ds.setMinIdle(2);
            Connection conn = ds.getConnection();

            System.out.println(conn);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库链接
     */
    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("数据源关闭");
        }
    }


}



/**
 * 表结构
 CREATE TABLE `tb_user` (
 `user_id` int(11) NOT NULL AUTO_INCREMENT,
 `user_name` varchar(20) DEFAULT NULL COMMENT '姓名',
 `user_sex` varchar(2) DEFAULT NULL COMMENT '性别',
 `user_birthday` date DEFAULT NULL COMMENT '出生日期',
 `user_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
 `user_edu` varchar(2) DEFAULT NULL COMMENT '学历',
 `user_telephone` varchar(30) DEFAULT NULL COMMENT '联系方式',
 `user_address` varchar(100) DEFAULT NULL COMMENT '住址',
 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 PRIMARY KEY (`user_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;


 * */
