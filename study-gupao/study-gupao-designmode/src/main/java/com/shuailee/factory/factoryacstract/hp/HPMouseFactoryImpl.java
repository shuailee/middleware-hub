package com.shuailee.factory.factoryacstract.hp;

import com.shuailee.factory.factoryacstract.MouseFactory;

public class HPMouseFactoryImpl implements MouseFactory {
    @Override
    public String createMouse() {
        return "惠普鼠標";
    }
}
