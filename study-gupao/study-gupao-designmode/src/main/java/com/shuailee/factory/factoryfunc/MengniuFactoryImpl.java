package com.shuailee.factory.factoryfunc;

import com.shuailee.factory.MengniuImpl;
import com.shuailee.factory.Milk;

/**
 * @program: study-gupao
 * @description: 蒙牛工厂
 * @author: shuai.li
 * @create: 2018-10-25 17:10
 **/
public class MengniuFactoryImpl implements Factory {
    @Override
    public Milk getMilk() {
        return new MengniuImpl();
    }
}
