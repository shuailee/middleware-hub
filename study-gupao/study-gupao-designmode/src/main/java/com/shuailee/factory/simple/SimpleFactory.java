package com.shuailee.factory.simple;

import com.shuailee.factory.MengniuImpl;
import com.shuailee.factory.Milk;
import com.shuailee.factory.YiliImpl;

import java.util.Calendar;

/**
 * @program: study-gupao
 * @description: 简单工厂
 * @author: shuai.li
 * @create: 2018-10-25 14:23
 **/
public class SimpleFactory {
    public Milk getMilk(String name) {
        if (name.equals("m")) {
            return new MengniuImpl();
        } else if (name.equals("y")) {
            return new YiliImpl();
        } else {
            return null;
        }

    }

    /**
     * 通过反射创建实例
     * */
    public Milk getMilk(Class clazz) {
        Object obj= null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (Milk)obj;
    }


}

