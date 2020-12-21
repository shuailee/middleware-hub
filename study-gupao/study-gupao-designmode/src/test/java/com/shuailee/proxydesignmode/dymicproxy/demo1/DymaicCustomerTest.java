package com.shuailee.proxydesignmode.dymicproxy.demo1;

import com.shuailee.proxydesignmode.staticproxy.demo1.Person;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

public class DymaicCustomerTest {
    @Test
    public void findObject() {
        try {
            Person obj = (Person)new DymaicProxyMedium().getInstance(new DymaicCustomer());
            obj.findObject();

            //从内存中的对象字节码通过文件流输出到一个新的class 文件，然后，通过反编译工具可以查看源代码
            byte [] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
            FileOutputStream os = new FileOutputStream("$Proxy0.class");
            os.write(bytes);
            os.close();
        } catch (Exception e) {

        }
    }

}