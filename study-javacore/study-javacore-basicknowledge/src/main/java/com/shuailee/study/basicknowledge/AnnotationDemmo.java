package com.shuailee.study.basicknowledge;

import com.shuailee.study.basicknowledge.annotationtest.Constraints;
import com.shuailee.study.basicknowledge.annotationtest.DBTable;
import com.shuailee.study.basicknowledge.annotationtest.SQLInteger;
import com.shuailee.study.basicknowledge.annotationtest.SQLString;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: study-javacore
 * @description: 自定义注解：组装数据库SQL的构建语句
 * @author: shuai.li
 * @create: 2018-06-29 15:29
 **/
public class AnnotationDemmo {

    public static  void main(String [] args){

         /*
        * 我们通过传递Member的全路径后通过Class.forName()方法获取到Member的class对象，然后利用Class对象中的方法获取所有成员字段Field，
        * 最后利用field.getDeclaredAnnotations()遍历每个Field上的注解再通过注解的类型判断来构建建表的SQL语句。这便是利用注解结合反射来构建SQL语句的简单的处理器模型
        * 类似于 ：Hibernate
        * */
        try {
            String sql = createTableSql("com.shuailee.study.basicknowledge.annotationtest.Member");
            System.out.println(sql);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    /**
     * desc: createTableSql 通过反射获取自定义注解的内容来创建sql语句
     * @parameter: [className]
     * @return: java.lang.String
     */
    public static String createTableSql(String className) throws ClassNotFoundException {

        //加载指定类的Class对象引用
        Class<?> clazz=Class.forName(className);

        // ***获取表名***
        //查找className是否有DBTable的注解
        DBTable dbTable= clazz.getAnnotation(DBTable.class);
        //如果没有表注解，直接返回
        if(dbTable == null) {
            System.out.println(
                    "No DBTable annotations in class " + className);
            return null;
        }
        String tableName=dbTable.name();
        //如果注解 name属性为空，则取类名作为表名
        if(tableName.length() < 1) {
            tableName = clazz.getName().toUpperCase();
        }

        //列集合
        List<String> columnDefs = new ArrayList<String>();

        //通过Class类API获取到所有成员字段(包括私有的)，然后再获取每个字段上的注解
        for(Field field :clazz.getDeclaredFields()){

            String columnName=null;
            //获取字段上的注解
            Annotation [] anns=field.getDeclaredAnnotations();
            //如果没加注解，则忽略此列
            if(anns.length<1){
                continue;
            }

            //获取注解 //instanceof  运行时判断对象是否是一个类的实例
            if(anns[0] instanceof SQLInteger){
                SQLInteger sint=(SQLInteger)anns[0];
                //如果注解 name属性为空，则取字段名作为列名.否则去注解name为列名
                if(sint.name().length()<1){
                    columnName=field.getName().toUpperCase();
                }else {
                    columnName=sint.name();
                }

                columnDefs.add(MessageFormat.format("{0} INT {1}",columnName,getConstraints(sint.constraint())));
            }

            //判断String类型
            if(anns[0] instanceof SQLString) {
                SQLString sString = (SQLString) anns[0];
                // Use field name if name not specified.
                if(sString.name().length() < 1) {
                    columnName = field.getName().toUpperCase();
                }
                else {
                    columnName = sString.name();
                }
                columnDefs.add(MessageFormat.format("{0} VARCHAR({1} {2})",columnName,sString.value(),getConstraints(sString.constraint())));
            }

        }//循环结束

        //数据库表构建语句
        StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
        for(String columndef : columnDefs){
            createCommand.append("\n"+columndef+",");
        }
        //Remove trailing comma 删除后面的逗号
        String tableCreate = createCommand.substring(
                0, createCommand.length() - 1) + ");";
        return tableCreate;

    }


    /**
     * desc: 判断该字段是否有其他约束
     * @parameter: [con]
     * @return: java.lang.String
     */
    private static String getConstraints(Constraints con) {
        String constraints = "";

        if(!con.allowNull()){

            constraints+=" NOT NULL";
        }
        if(con.primarykey()){
            constraints+=" PRIMARY KEY";
        }
        if(con.unique()) {
            constraints += " UNIQUE";
        }
        return constraints;
    }
}







