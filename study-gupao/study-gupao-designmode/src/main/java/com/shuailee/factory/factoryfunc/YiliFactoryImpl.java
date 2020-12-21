package com.shuailee.factory.factoryfunc;

import com.shuailee.factory.MengniuImpl;
import com.shuailee.factory.Milk;
import com.shuailee.factory.YiliImpl;

/**
 * @program: study-gupao
 * @description: 伊利工厂
 * @author: shuai.li
 * @create: 2018-10-25 17:10
 **/
public class YiliFactoryImpl implements Factory {
    @Override
    public Milk getMilk() {
        return new YiliImpl();
    }
}
