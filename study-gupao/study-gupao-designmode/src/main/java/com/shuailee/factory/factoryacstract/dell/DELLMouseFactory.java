package com.shuailee.factory.factoryacstract.dell;

import com.shuailee.factory.factoryacstract.MouseFactory;

public class DELLMouseFactory  implements MouseFactory {
    @Override
    public String createMouse() {
        return "dell鼠標";
    }
}
