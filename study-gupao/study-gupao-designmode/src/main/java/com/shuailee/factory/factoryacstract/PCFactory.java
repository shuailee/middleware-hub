package com.shuailee.factory.factoryacstract;

import com.shuailee.factory.Milk;
import com.shuailee.factory.factoryfunc.Factory;

/**
 * @program: study-gupao
 * @description: 電腦的抽象工厂，實現該工廠的必須能生產鼠標和鍵盤
 * @author: shuai.li
 * @create: 2018-10-25 15:00
 **/
public interface PCFactory {

     KeyboFactory createKeybo();
     MouseFactory createMouse();

}
