package com.shuailee.factory;

import static org.junit.Assert.assertTrue;

import com.shuailee.singleton.LazySingletonTwo;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Unit test for simple App.
 */
public class AppSimpleTest
{
    /**
     * Rigorous SimpleTest :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        try {
            //通过反射进行破坏
            Class<?> clazz = LazySingletonTwo.class;
            //通过反射拿到私有的构造方法
            Constructor c =clazz.getDeclaredConstructor(null);
            //强制访问
            c.setAccessible(true);
            Object o1 = c.newInstance();
            Object o2 = c.newInstance();
            System.out.println(o1==o2);
            //assertTrue(o1 == o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
