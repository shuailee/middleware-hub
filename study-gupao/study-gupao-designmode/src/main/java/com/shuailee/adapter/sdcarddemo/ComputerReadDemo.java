package com.shuailee.adapter.sdcarddemo;

import com.shuailee.adapter.sdcarddemo.Computer.Computer;
import com.shuailee.adapter.sdcarddemo.Computer.ThinkpadComputer;
import com.shuailee.adapter.sdcarddemo.SD.SDCard;
import com.shuailee.adapter.sdcarddemo.SD.SDImpl;
import com.shuailee.adapter.sdcarddemo.TF.TFCard;
import com.shuailee.adapter.sdcarddemo.TF.TFImpl;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-03 14:40
 **/
public class ComputerReadDemo {
    public static void main(String[] args) {
        //SD卡在电脑上读取
        SDCard sdCard=new SDImpl();
        Computer computer=new ThinkpadComputer(sdCard);
        computer.readSD();

        //电脑值暂时支持SD卡的读取，如果需要读取TF卡需要使用SD卡读取器适配
        //计算机通过SD读卡器读取TF卡
        TFCard tfCard = new TFImpl();
        SDCard tfCardAdapterSD = new SDAdapterTF(tfCard);
        Computer computer1=new ThinkpadComputer(tfCardAdapterSD);
        computer1.readSD();
    }
}
