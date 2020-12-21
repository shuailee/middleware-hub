package com.shuailee.factory.factoryacstract.dell;

import com.shuailee.factory.factoryacstract.KeyboFactory;

public class DELLKeyboFactory implements KeyboFactory {
    @Override
    public String createKeybo() {
        return "dell鍵盤";
    }
}
