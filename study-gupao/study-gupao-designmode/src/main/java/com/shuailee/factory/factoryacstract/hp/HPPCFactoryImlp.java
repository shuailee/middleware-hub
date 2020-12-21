package com.shuailee.factory.factoryacstract.hp;

import com.shuailee.factory.factoryacstract.KeyboFactory;
import com.shuailee.factory.factoryacstract.MouseFactory;
import com.shuailee.factory.factoryacstract.PCFactory;

public class HPPCFactoryImlp implements PCFactory {
    @Override
    public KeyboFactory createKeybo() {
        return new HPKeyboFactoryImpl();
    }

    @Override
    public MouseFactory createMouse() {
        return new HPMouseFactoryImpl();
    }
}
