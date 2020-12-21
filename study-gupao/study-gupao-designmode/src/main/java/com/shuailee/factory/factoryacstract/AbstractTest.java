package com.shuailee.factory.factoryacstract;


import com.shuailee.factory.factoryacstract.dell.DELLPCFactoryImlp;
import com.shuailee.factory.factoryacstract.hp.HPPCFactoryImlp;

public class AbstractTest {
    public static void main(String[] args) {
        //PCFactory factory= new HPPCFactoryImlp();
        PCFactory factory= new DELLPCFactoryImlp();
        System.out.println(factory.createKeybo().createKeybo());
        System.out.println(factory.createMouse().createMouse());


    }
}
