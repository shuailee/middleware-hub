package com.shuailee.delegatepattern.demo1;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: study-gupao
 * @description: 委派角色, 是整个模式的核心角色,它负责在各个具体角色实例之间做出决策, 由它判断并调用具体实现的方法
 * 委派模式对外隐藏了具体实现, 仅将委派者角色暴露给外部, 如Spring的DispatcherServlet.
 * @author: shuai.li
 * @create: 2019-05-09 21:57
 **/
public class RoleDelegate implements DelegateTask {

    private Map<String,DelegateTask> targets=new HashMap<String,DelegateTask>();

    public RoleDelegate(){
        targets.put("login",new ConcreteDelegateTaskA());
        targets.put("sendmsg",new ConcreteDelegateTaskA());
    }

    @Override
    public void doTask(String command) {
        System.out.println("委派执行开始....");
        targets.get(command).doTask(command);
        System.out.println("委派执行结束....");
    }
}
