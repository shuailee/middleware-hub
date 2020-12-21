package com.shuailee.study.ut.powermock;


import com.shuailee.study.ut.demo.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Method;


@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*"})
@PrepareForTest({CustomerProcess.class})
public class CustomerProcessTest {

    /**
     * 注入一个真实的对象（相对于mock对象而言）
     * */
    @InjectMocks
    private CustomerProcess customerProcess;

    @Before
    public void setUp() throws Exception {
        //只mock类中的其中一个方法的返回（用于要测试的一个方法依赖于另外一个方法，而你不希望关心依赖方法的情况）
        Method checkRepeaterMethod = PowerMockito.method(CustomerProcess.class, "checkRepeater", Long.class);
        PowerMockito.replace(checkRepeaterMethod).with((proxy, method1, args) -> true);
    }

    @Test
    public void testSave(){
        Assert.assertEquals(true,customerProcess.save(1213L,new User(1213L,"AA")));
    }

}
