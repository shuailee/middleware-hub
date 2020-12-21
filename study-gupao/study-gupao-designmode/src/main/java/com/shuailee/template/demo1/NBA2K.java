package com.shuailee.template.demo1;

/**
 * @program: study-gupao
 * @description: NBA2K Game
 * @author: shuai.li
 * @create: 2019-05-28 20:10
 **/
public class NBA2K extends AbstractGame {
    @Override
    void initialize() {
        System.out.println("初始化NBA2K游戏");
    }

    @Override
    void startPlay() {
        System.out.println("开始NBA2K游戏");
    }

    @Override
    void endPlay() {
        System.out.println("结束NBA2K游戏");
    }
}
