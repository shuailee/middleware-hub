package com.shuailee.adapter.sdcarddemo.SD;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-03 14:35
 **/
public class SDImpl implements SDCard {
    @Override
    public void readSD() {
        System.out.println("READ SD");
    }

    @Override
    public void writeSD() {
        System.out.println("WRITE SD");
    }
}
