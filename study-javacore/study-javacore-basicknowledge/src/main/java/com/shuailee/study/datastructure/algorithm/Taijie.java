package com.shuailee.study.datastructure.algorithm;
/**
 * 动态规划 台阶问题
 * */
public class Taijie {

    int f(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return f(n-1) + f(n-2);
    }
}
