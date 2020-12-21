package com.shuailee.study.ut.mockito;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MockitoTest {
    /**
     * mock函数：默认情况下，所有的函数都有返回值。mock函数默认返回的是null，一个空的集合或者一个被对象类型包装的内置类型，例如0、false对应的对象类型为Integer、Boolean。
     * when....thenReturn  为测试桩，设定方法的返回，后面使用断言验证
     * anyInt()内置参数匹配器，任意int类型
     */
    @Test
    public void mocktest1() {
        // 使用 mock 静态方法创建 Mock 对象,Mock本质是创造个假对象，伪装使用
        Map map = mock(Map.class);
        Assert.assertTrue(map instanceof Map);

        //当调用map.size()方法时候，返回100
        when(map.size()).thenReturn(100);
        Assert.assertEquals(map.size(), 100);

        //当调用map.put(1，2)方法时候，返回true，参数要匹配
        when(map.put(1, 2)).thenReturn(true);
        Assert.assertTrue((Boolean) map.put(1, 2));

        //当调用map.get(1)方法时候，抛空指针
        doThrow(new NullPointerException()).when(map).get(1);

    }

    @Test
    public void mocktest2() {
        // mock 方法不仅可以 Mock 接口类, 还可以 Mock 具体的类型.
        HashMap mock = mock(HashMap.class);
        Assert.assertTrue(mock instanceof Map);
        Assert.assertTrue(mock instanceof HashMap);
    }

    @Test
    public void mocktest3(){
        Iterator i= mock(Iterator.class);
        // 返回多个值
        when(i.next()).thenReturn("mic").thenReturn("tina");
        String result=i.next()+"-"+i.next();
        org.junit.Assert.assertEquals("mic-tina",result);
    }

    @Test
    public void mocktest4(){
        Comparable c=mock(Comparable.class);
        // 如何根据输入来返回值
        when(c.compareTo("mic")).thenReturn(1);
        when(c.compareTo("tina")).thenReturn(2);
        org.junit.Assert.assertEquals(1,c.compareTo("mic"));

        //让返回值不依赖于输入
        when(c.compareTo(anyInt())).thenReturn(-1);
        org.junit.Assert.assertEquals(-1,c.compareTo(9999));

    }

    @Test
    public void mockverify(){
        HashMap mockedList = mock(HashMap.class);
        Assert.assertEquals(mockedList.size(),0);

        //验证方法调用次数
        verify(mockedList, times(1)).size();

        //验证行为是否调用过
        mockedList.clear();
        mockedList.put("one",1);
        verify(mockedList).clear();
        verify(mockedList).put("one",1);

        // 使用never()进行验证,never相当于times(0)
        //使用atLeast()/atMost() 最少做多等
        verify(mockedList, never()).put("two","never happened");

        //使用atLeast()/atMost() 最少做多等
        //verify(mockedList, atLeast(2)).put("","five times");
        //verify(mockedList, atMost(5)).put("","three times");
    }


    /**
     * 验证执行顺序
     * 一个对象的两个方法调用
     * */
    @Test
    public void mockorderA()
    {
        // A. Single mock whose methods must be invoked in a particular order
        // A. 验证mock一个对象的函数执行顺序
        List singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        //create an inOrder verifier for a single mock
        // 为该mock对象创建一个inOrder对象
        InOrder inOrder = inOrder(singleMock);

        //following will make sure that add is first called with "was added first, then with "was added second"
        // 确保add函数首先执行的是add("was added first"),然后才是add("was added second")
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");
    }
    /**
     * 两个对象的执行顺序
     * */
    @Test
    public void  mockorderB(){
        // B. Multiple mocks that must be used in a particular order
        // B .验证多个mock对象的函数执行顺序
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //create inOrder object passing any mocks that need to be verified in order
        // 为这两个Mock对象创建inOrder对象
        InOrder inOrder = inOrder(firstMock, secondMock);

        //following will make sure that firstMock was called before secondMock
        // 验证它们的执行顺序
        inOrder.verify(firstMock).add("was called first");
        inOrder.verify(secondMock).add("was called second");

    }
}
