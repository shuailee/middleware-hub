package com.shuailee.adapter.sdcarddemo.Computer;

import com.shuailee.adapter.sdcarddemo.SD.SDCard;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-03 14:37
 **/
public class ThinkpadComputer implements  Computer{
    SDCard sdCard;
    public ThinkpadComputer(SDCard sdCard){
        this.sdCard=sdCard;
    }
    @Override
    public void readSD() {
        if(sdCard == null) {
            throw new NullPointerException("sd card null");
        }
        sdCard.readSD();
    }
}
