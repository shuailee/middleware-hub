package com.shuailee.delegatepattern.demo1;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-05-09 22:01
 **/
public class TaskTest {
    public static void main(String[] args) {
        //看上去好像是委派角色RoleDelegate在执行任务，实际上是由内部具体任务角色在执行
        //Spring MVC框架中的DispatcherServlet其实就用到了委派模式
        RoleDelegate roleDelegate=new RoleDelegate();
        roleDelegate.doTask("login");
        roleDelegate.doTask("sendmsg");

    }
}
