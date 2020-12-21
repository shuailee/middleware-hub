package com.shuailee.delegatepattern.demo1;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-05-09 21:39
 **/
public class ConcreteDelegateTaskA implements DelegateTask {

    @Override
    public void doTask(String command) {
        System.out.println("A开始执行任务："+command);
    }
}
