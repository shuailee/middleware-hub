package com.shuailee.study.basicknowledge.collectorstest;

import java.util.TreeMap;

/**
 * @program: study-javacore
 * @description:
 * @create: 2019-11-04 11:35
 **/
public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<Integer, String>();
        map.put(8, "8");
        map.put(5, "5");
        map.put(1, "1");
        map.put(3, "3");
        map.put(2, "2");
        map.put(6, "6");
        System.out.println(map);

        System.out.println(map.lastKey());
    }
}
