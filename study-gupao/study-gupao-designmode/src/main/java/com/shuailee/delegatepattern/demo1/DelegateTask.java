package com.shuailee.delegatepattern.demo1;

/**
 * @program: study-gupao
 * @description: 抽象任务角色,它的实现着是具体任务角色
 * @author: shuai.li
 * @create: 2019-05-09 21:32
 **/
public interface DelegateTask {
    void doTask(String command);
}
