package com.shuailee.adapter.sdcarddemo.TF;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-03 14:42
 **/
public class TFImpl implements TFCard {
    @Override
    public void readTF() {
        System.out.println("Read TF");
    }

    @Override
    public void writeTF() {
        System.out.println("Write TF");
    }
}
