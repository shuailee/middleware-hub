package com.shuailee.singleton;

import java.io.Serializable;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-04-16 13:59
 **/
public class SeriableSingleton implements Serializable {
    private SeriableSingleton(){}
    private final static SeriableSingleton instance=new SeriableSingleton();

    public static SeriableSingleton getInstance(){
        return instance;
    }

    //通过readResolve()方法返回被创建的实例解决了单例在反序列化后被破坏的问题
    //但是内部事实上也创建了一个新的实例，只不过没返回，始终返回的是第一次创建的那个
    private Object readResolve(){
        return instance;
    }
}
