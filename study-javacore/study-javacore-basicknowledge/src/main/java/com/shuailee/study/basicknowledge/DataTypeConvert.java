package com.shuailee.study.basicknowledge;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: study-javacore
 * @description: 数据类型转换
 * @author: shuai.li
 * @author: shuai.li
 * @author: shuai.li
 * @author: shuai.li
 * @author: shuai.li
 * @author: shuai.li
 * @uthor: shuai.li
 * @create: 2018-06-26 19:40
 **/
public class DataTypeConvert {

    //region --介绍

            /*
        * 1 java数据类型氛围内置数据类型和引用数据类型
        * 内置数据类型有： byte short int long float double boolean char
        * 每一种内置数据类型都有其对应的包装类型 Byte Short Integer Long Float Double Bool Character,包装类型都是引用类型数据
        * 2 基本数据类型转换
        * （1）从低  ------------------------------------>  高 隐式转换
        *      byte,short,char—> int —> long—> float —> double
        * （2）把容量大的往小了转换必须使用强转，且可能会丢失精度：int i =128; byte b = (byte)i;
        * （3）浮点数到整数的转换是通过舍弃小数得到，而不是四舍五入
        * 3 基本数据类型和包装数据类型转换
        * (1)简单类型的变量转换为相应的包装类，可以利用包装类的构造函数。即：Boolean(boolean value)、Character(char value)、Integer(int value)、Long(long value)、
        * Float(float value)、Double(double value)，例如Float f1=new Float(100.02f);
        * (2)而在各个包装类中，总有形为××Value()的方法，来得到其对应的简单类型数据,利用这种方法，也可以实现不同数值型变量间的转换，例如x.intValue
        * (3)每种包装类型都有valueOf()方法来将其基本类型或字符串转为当前包装类型，String s=String.valueOf(d);
        * (4)包装类型的静态parseXXX方法进行类型转换
        * (5)数字运算表达式，因为先进行等式右边的运算，再赋值给等式左边的变量，所以等式两边的类型要一致
        * */
    //endregion

    public static void main(String[] args) {
        numberConvert();


        //JodaTimeTest();
        /*timediff();
        decimalDiff();
        baiscDataType();
        timeConvert();*/
    }

    /**
     * 数字运算表达式，因为先进行等式右边的运算，再赋值给等式左边的变量，所以等式两边的类型要一致
     */
    private static void numberConvert() {
        //int 类型相除或者其他计算，结果都会被转成int整数类型
        double d = 24 / 7;
        //所以在需要返回非整数类型时，计算后要转型
        double d2 = (double) 24 / 7;
        System.out.println(d);
        System.out.println(d2);


        System.out.println(Integer.MAX_VALUE);

        //超出int长度 会溢出
        //使用com.google.common.math类检查数值是否会溢出
        long i = Integer.MAX_VALUE + 1;
        //不会溢出
        long ii = Integer.MAX_VALUE + 1L;
        System.out.println(i);
        System.out.println(ii);
    }


    public static void JodaTimeTest() {
        String STANDARD_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

        Calendar calendar = GregorianCalendar.getInstance();
        String s = newDateFormat(STANDARD_DATE_FORMAT, calendar.getTimeZone()).format(calendar.getTime());
        System.out.println(s);

      /*  String date="2019-11-04T19:46:26.583+08:00";
        GregorianCalendar result= DateTime.parse(date).toGregorianCalendar();
        System.out.println(result.getTimeZone());*/


    }


    public static DateFormat newDateFormat(String formart, TimeZone timeZone) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(formart);
        if (timeZone != null) {
            dateFormat.setTimeZone(timeZone);
        }

        return dateFormat;
    }

    /**
     * desc: baiscDataType 基本数据类型转换
     *
     * @parameter: []
     * @return: void
     */

    public static void baiscDataType() {


        byte b = 110;
        int i = b;
        System.out.println(String.format("1 byte->int:  byte:%s，int:%s", b, i));

        b = (byte) i;//需要强转，可能会丢失精度
        System.out.println(MessageFormat.format("2 int->byte:  byte:{0}，int:{1}", b, i));

        Float f = new Float(100.02f);//简单类型转为包装类型
        i = f.intValue();
        System.out.println(String.format("3 包装类型转为基本类型 Float->int:  Float:%s，int:%s", f, i));

        Double d = f.doubleValue();//
        System.out.println(String.format("4 包装类型转为其他数值类型 Float->Double:  Float:%s，Double:%s", f, d));


        String si = String.valueOf(i);//int->String
        System.out.println(String.format("5 其他类型转为包装类型，.valueOf转换 int->String:  String:%s，int:%s", si, i));
        //Double->String
        String s = String.valueOf(d);
        Double dd = Double.parseDouble(s); //String->Double
        System.out.println(String.format("6 Double.parseDouble转换：  String->Double:  String:%s，Double:%s", s, dd));

        int price = 5;
        BigDecimal price_decimal = new BigDecimal(price);
        System.out.println(MessageFormat.format("7 int->BigDecimal, {0}的数据类型是{1}", price_decimal, price_decimal.getClass().getName()));
        Integer pt = price_decimal.intValue();
        System.out.println(MessageFormat.format("8 BigDecimal->int ,{0}的数据类型是{1}", pt, pt.getClass().getName()));


    }

    /**
     * desc: timeConvert时间数据类型转换
     *
     * @parameter: []
     * @return: void
     */

    public static void timeConvert() {

        //new Date()获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式,yyyy-MM-dd 不同的格式输出不同的结果
        Date time1 = new Date();// new Date()为获取当前系统时间
        String time1str = df.format(time1);
        long time1long = time1.getTime();
        Date date2 = new Date(time1long); //long转date

        System.out.println(MessageFormat.format("11 new Date() 获取当前时间{0}，date 转long(毫秒)：{2}， long转date（毫秒）: {3}", time1str, time1long, date2.getTime()));

        //Calendar 获取当前时间
        String time2 = df.format(Calendar.getInstance().getTime());
        System.out.println(MessageFormat.format("22 Calendar的getTime() 获取当前时间:{0}", time2));


        try {
            //string转Calendar
            String timestr3 = "2018-05-06 10:20:08";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            //1 string 先转date 需要加 try catch
            Date dt = sdf.parse(timestr3);
            //2 data 转 calendar
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt);

            //calendar.getTime();// Calendar 转 data
            System.out.println(MessageFormat.format("33 string转Calendar获取到的毫秒数:{0}", calendar.getTimeInMillis()));
        } catch (Exception ex) {
            int i = 0;

        }
        //long 转 calendar
        long time4 = System.currentTimeMillis() + 100000000;
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(time4);
        System.out.println(MessageFormat.format("44 long 转 calendar:{0}", calendar2.getTimeInMillis()));
    }

    /**
     * desc: timediff时间差的计算
     *
     * @parameter: []
     * @return: void
     */
    public static void timediff() {
        //时间加减
        try {
            String time = "2018/06/26";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date dt = sdf.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt);
            Calendar calendar2 = calendar;
            //加上2天
            calendar.add(Calendar.DATE, 2);

            String time2 = sdf.format(calendar.getTime());
            System.out.println(MessageFormat.format("{0}时间上加上两天:{1}.calendar大（晚）于calendar2:{2}", time, time2, calendar.after(calendar2)));

        } catch (Exception ex) {
            System.out.println(String.format("有异常%s", ex.getMessage()));
        }

    }

    /**
     * desc: decimalDiff数值比大小
     *
     * @parameter: []
     * @return: void
     */
    public static void decimalDiff() {

        //BigDecimal比大小 数值比大小
        double p1 = 10.23;
        //int 转 BigDecimal
        BigDecimal p2 = new BigDecimal(11);
        int a = BigDecimal.valueOf(p1).compareTo(p2);
        //a = -1,表示p1小于p2；
        //a = 0,表示p1等于p2；
        //a = 1,表示p1大于p2；
        if (a == -1) {
            System.out.println(String.format("a== %s,p1小于p2", a));
        } else if (a == 0) {
            System.out.println(MessageFormat.format("a=={0},p1等于p2", a));
        } else if (a == 1) {
            System.out.println(MessageFormat.format("a=={0},p1大于p2", a));
        }

        if (!p2.equals(BigDecimal.ZERO)) {
            BigDecimal pp1 = BigDecimal.valueOf(p1);
            BigDecimal p3 = p2.add(pp1);
            System.out.println(MessageFormat.format("p2+p1={0}", p3));
            BigDecimal p4 = p2.subtract(pp1);
            System.out.println(MessageFormat.format("p2+p1={0}", p4));
        }

    }

}
