package com.shuailee.factory.factoryacstract.dell;

import com.shuailee.factory.factoryacstract.KeyboFactory;
import com.shuailee.factory.factoryacstract.MouseFactory;
import com.shuailee.factory.factoryacstract.PCFactory;

public class DELLPCFactoryImlp implements PCFactory {
    @Override
    public KeyboFactory createKeybo() {
        return new DELLKeyboFactory();
    }

    @Override
    public MouseFactory createMouse() {
        return  new DELLMouseFactory();
    }
}
