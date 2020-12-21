package com.shuailee.common;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.text.MessageFormat;
import java.util.Properties;


/**
 * @program: study-gupao
 * @description: 开发一个自己的监控的插件，用于监控每个SQL执行的真正时长
 * 使用插件是非常简单的，只需实现 Interceptor 接口，并指定想要拦截的方法签名即可
 * @author: shuai.li
 * @create: 2019-06-18 21:09
 **/

//插件将会拦截在 Executor 实例中所有的 “update”,"query" 方法调用， 这里的 Executor 是负责执行低层映射语句的内部对象
@Intercepts({
@Signature(type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
@Signature(type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
@Signature(type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})
})
public class MonitorSQLExecutionTimePlugin implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //获取invocation中执行的sql
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object param = args[1];
        BoundSql boundSql = mappedStatement.getBoundSql(param);
        String sql = boundSql.getSql();

        //计算时间并打印
        long beginTime = System.currentTimeMillis();
        Object result = invocation.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println(MessageFormat.format("sql : {0}, 自定义插件elapsed time {1} ms", sql, endTime - beginTime));
        return result;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}


/**
 Mybatis允许在SQL执行过程某些点进行拦截。默认情况，Mybatis允许的调用拦截点有：

 Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
 ParameterHandler (getParameterObject, setParameters)
 StatementHandler (prepare, parameterize, batch, update, query)
 ResultSetHandler (handleResultSets, handleOutputParameters)

 Executor 扮演者执行器的角色，是所有的SQL执行的总入口；
 ParameterHandler 负责对SQL语句的输入参数进行处理；
 StatementHandler 负责SQL语句执行调用、Statement的初始化、调用ParameterHandler将输入参数绑定到Statement上；
 ResultSetHandler 对执行的结果集和实体进行映射处理。
 可以看出Mybatis在SQL执行前、输入参数处理、SQL执行、结果集处理等这些重要的步骤中都预留了丰富的拦截点

 * */
