package com.shuailee.study.basicknowledge;


import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: study-javacore
 * @description:
 * @author: shuai.li
 * @create: 2018-06-26 12:13
 **/
public class Demo {

    public static void main(String [] args)
    {


        List<String> str=new ArrayList<String>();
        System.out.println(str.contains(null));

        HashMap<String,String> m=new HashMap<String, String>();
        System.out.println(m.containsKey(null));
        System.out.println("hello world");
        new Demo().process();
        //https://www.cnblogs.com/shaner/p/7192368.html
        int s=Yunsuan.showTicket|Yunsuan.showRebooking|Yunsuan.showRefund;
        System.out.println("s:"+s);
        int showticket=s&Yunsuan.showTicket;
        System.out.println("showticket:"+showticket);
        int showRebooking=s&Yunsuan.showRebooking;
        System.out.println("showRebooking:"+showRebooking);
        int showRefund=s&Yunsuan.showRefund;
        System.out.println("showRefund:"+showRefund);

    }

    /**
    * //使用@Test注解后，在运行该方法时，测试框架会自动识别该方法并单独调用
    * //@Test实际上是一种标记注解，起标记作用，运行时告诉测试框架该方法为测试方法
    * */
    @Test
    public void process()
    {
        System.out.println("你好process");
    }

    @Deprecated //过时注解
    @SuppressWarnings("uncheck") //忽略警告提示注解
    public void process2()
    {
        System.out.println("你好process2");
    }


    public void process3(){
        String s= new BigDecimal(120)
                .divide(new BigDecimal(60),1,BigDecimal.ROUND_HALF_UP) //除法四舍五入保留1位小数
                .stripTrailingZeros()//清除小数点后多余的0
                .toPlainString();//转为string格式
        System.out.println(s);
    }

}


class Yunsuan{

    public static final int showTicket=0x0001;
    public static final int showRebooking=0x0002;
    public static final int showRefund=0x0004;
}
