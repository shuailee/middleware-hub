package com.shuailee.template.demo1;

/**
 * @program: study-gupao
 * @description: 绝地求生
 * @author: shuai.li
 * @create: 2019-05-28 20:12
 **/
public class PUBG extends AbstractGame {
    @Override
    void initialize() {
        System.out.println("初始化绝地求生游戏");
    }

    @Override
    void startPlay() {
        System.out.println("开始绝地求生游戏");
    }

    @Override
    void endPlay() {
        System.out.println("结束绝地求生游戏");
    }
}
