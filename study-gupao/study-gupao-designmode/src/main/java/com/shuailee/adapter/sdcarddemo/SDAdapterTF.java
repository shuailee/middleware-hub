package com.shuailee.adapter.sdcarddemo;

import com.shuailee.adapter.sdcarddemo.SD.SDCard;
import com.shuailee.adapter.sdcarddemo.TF.TFCard;

/**
 * @program: study-gupao
 * @description: 实现SDCard接口，并将要适配的对象作为适配器的属性引入
 * 创建SD适配TF （也可以说是SD兼容TF，相当于读卡器）：
 * @author: shuai.li
 * @create: 2019-06-03 14:49
 **/
public class SDAdapterTF implements SDCard {

    private TFCard tfCard;
    public SDAdapterTF(TFCard tfCard){
        this.tfCard = tfCard;
    }
    @Override
    public void readSD() {
        System.out.println("adapter read tf card ");
        tfCard.readTF();
    }

    @Override
    public void writeSD() {
        System.out.println("adapter write tf card");
        tfCard.writeTF();
    }
}
