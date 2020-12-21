package com.shuailee.factory.factoryacstract.hp;

import com.shuailee.factory.factoryacstract.KeyboFactory;

public class HPKeyboFactoryImpl implements KeyboFactory {
    @Override
    public String createKeybo() {
        return "惠普鍵盤";
    }
}
