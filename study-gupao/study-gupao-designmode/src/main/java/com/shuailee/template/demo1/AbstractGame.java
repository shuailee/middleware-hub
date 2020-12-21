package com.shuailee.template.demo1;

/**
 * @program: study-gupao
 * @description: 模板方法模式，规范业务方法的执行顺序。将相同执行流程的业务抽象到模板类中
 * @author: shuai.li
 * @create: 2019-05-28 19:03
 **/
public abstract  class AbstractGame {
    /**
     * 初始化游戏
     * */
    abstract void initialize();
    /**
     * 开始游戏
     * */
    abstract void startPlay();
    /**
     * 结束游戏
     * */
    abstract void endPlay();

    public final void process(){
        initialize();
        startPlay();
        endPlay();
    }
}


