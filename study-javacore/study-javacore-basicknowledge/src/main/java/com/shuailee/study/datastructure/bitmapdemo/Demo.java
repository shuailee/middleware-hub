package com.shuailee.study.datastructure.bitmapdemo;

import java.util.BitSet;

/**
 * @program: study-javacore
 * @description: Demo
 * @author: shuai.li
 * @create: 2020-07-02 14:24
 **/
public class Demo {
    /**
    * BitSet初始分配2^3个bit
    * */
    static final int DEFAULT_SIZE = 1 << 3;

    public static void main(String[] args) {
        bitSort();
    }

    private static void bitSort() {
        /**
         * BitSet实现了一个位向量，它可以根据需要增长。每一位都有一个布尔值。一个BitSet的位可以被非负整数索引（PS：意思就是每一位都可以表示一个非负整数）。
         * 可以查找、设置、清除某一位。通过逻辑运算符可以修改另一个BitSet的内容。默认情况下，所有的位都有一个默认值false。
         * */
        int[] num = {4, 7, 2, 5, 3};
        BitSet bitSet = new BitSet(DEFAULT_SIZE);
        for (int i : num) {
            bitSet.set(i,true);
        }
        System.out.println(bitSet.get(4));
        System.out.println(bitSet);
    }
}
