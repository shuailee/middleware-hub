package com.shuailee.study.ut.jut4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
//Parameterized：根据所设计的参数来执行测试。假设我们要测试某一个方法，它有两个参数，每个参数需要设计不同值，那么我们最开始就是需要为每个参数设计一个测试方法，
// 这样就很麻烦，10种case就得10个方法，但是有了Parameterized runner，我们可以设计一个方法，多种参数来执行test case
// 下面就是测试了Parameterized runner， 参数会自动匹配。它其实就是，看我们传入几种case， 也就是List.size()，然后，把类里面的方法，循环重复执行size()数目。

@RunWith(Parameterized.class)
public class ParameterizedCaculatorClassTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    //参数会自动匹配
    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[]{-1, 1, 0}, new Object[]{1, 1, 2});
    }

    @Parameterized.Parameter(value = 0)
    public int o1;
    @Parameterized.Parameter(value = 1)
    public int o2;
    @Parameterized.Parameter(value = 2)
    public int expector;

    /**
     * 上面被标记了的Parameters的data  会被逐个带入到当前测试用例
     * */
    @Test
    public void test() throws IOException, RuntimeException {
        CaculatorClass cal = new CaculatorClass();
        assertEquals(expector, cal.sum(o1, o2));
    }

    @Test
    public void testO1Exception() {
        CaculatorClass cal = new CaculatorClass();
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("o1 is too big");
        cal.sum(300, 100);
    }

    @Test
    public void testO2Exception() {
        CaculatorClass cal = new CaculatorClass();
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("o2 is too big");
        cal.sum(100, 300);
    }


}


