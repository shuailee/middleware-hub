package com.shuailee.study.ut.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static com.shuailee.study.ut.powermock.SingletonDemo.checkVIP;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * @program: study-javacore
 * @description:
 * @create: 2019-08-06 21:27
 **/
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*"})
@PrepareForTest({SingletonDemo.class})
public class SingletonDemoTest {

    /**
     * 静态方法的mock
     * */
    @Test
    public void testCheckvip() throws Exception {
        PowerMockito.mockStatic(SingletonDemo.class);
        PowerMockito.when(SingletonDemo.checkVIP(anyString())).thenReturn(true);
        Assert.assertEquals(true,SingletonDemo.checkVIP("E11"));
    }

    /**
     * 单例类的mock
     * 思路就是静态方法mock一个类，非静态方法也mock一个类，访问getInstance单例访问点时返回实例mock的对象
     * */
    @Test
    public void testGetInstance(){
        //这行可以使用注解
        SingletonDemo singletonDemo=PowerMockito.mock(SingletonDemo.class);
        //这两行可以卸载setUp里
        PowerMockito.mockStatic(SingletonDemo.class);
        PowerMockito.when(SingletonDemo.getInstance()).thenReturn(singletonDemo);

        PowerMockito.when(singletonDemo.getCustomer(anyString())).thenReturn(true);
        Assert.assertEquals(true,SingletonDemo.getInstance().getCustomer("E11"));
    }
}
