package com.shuailee.study.ut.mockito;

import com.shuailee.study.ut.demo.MyRepository;
import com.shuailee.study.ut.demo.MyService;
import com.shuailee.study.ut.demo.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

/**
 * 可以使用注解@RunWith(xxxx)来指定运行测试的执行器。
 * Runner-测试执行器  你写的测试代码都会在这些测试执行器中运行，Junit默认的执行器为BlockJUnit4ClassRunner，
 * */
@RunWith(MockitoJUnitRunner.class)
public class ZhujieMockTest {

    @Mock
    private MyRepository myRepository;
    @InjectMocks
    private MyService myService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(myRepository.insert(any(User.class))).thenReturn(1);
    }
    @Test
    public void testInjectMocks() {
        /**
         * MyService 被标记了 @InjectMocks，在 setUp方法中 执行 MockitoAnnotations.initMocks(this); 的时候，
         * 会将标记了 @Mock 或 @Spy 的属性注入到 service 中。MyService 里面的 MyRepository 完全被Mock实例替换，
         * 所有的调用都是针对Mock生成类的。
         * */
        long id=1001;
        String name="kaka";
        Assert.assertEquals(true,myService.insert(id,name));
        //System.out.println(myService.getMyRepository().getClass());
    }


}
