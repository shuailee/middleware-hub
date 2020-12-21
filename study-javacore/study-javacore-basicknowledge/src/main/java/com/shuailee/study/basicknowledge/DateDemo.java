package com.shuailee.study.basicknowledge;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @program: study-javacore
 * @description:
 * @author: shuai.li
 * @create: 2019-05-30 12:14
 **/
public class DateDemo {

    /**
     *  用Java处理时间时，我们可能会经常发现时间不对，比如相差8个小时等等，其真实原因便是TimeZone
     *
     * 1  GMT时间：即格林威治平时（Greenwich Mean Time）。平太阳时是与视太阳时对应的，由于地球轨道非圆形，运行速度随地球与太阳距离改变而出现变化，因此视太阳时欠缺均匀性。
     *  为了纠正这种不均匀 性，天文学家就计算地球非圆形轨迹与极轴倾斜对视太阳时的效应，而平太阳时就是指经修订之后的视太阳时。在格林威治子午线上的平太阳时称为世界时（UTC），
     *  又叫格林威治平时（GMT）
       2 Date对象本身所存储的毫秒数可以通过date.getTime()方法得到；该函数返回自1970年1月1日 00:00:00 GMT以来此对象表示的毫秒数。它与时区和地域没有关系(其实可以认为是GMT时间)，
         而且还会告诉我们这个时区是否使用夏令时。有个这个信息，我们就能够继续将时区对象和日期格式化器结合在一起在其它的时区和其它的语言显示时间了。
         获得一个表示当前时间的Date对象有两种方式:Date date = new Date(); 或者  Date date = Calendar.getInstance().getTime();

       3 Calendar的getInstance()方法有参数为TimeZone和Locale的重载，可以使用指定时区和语言环境获得一个日历。无参则使用默认时区和语言环境获得日历。
       4 TimeZone对象给我们的是原始的偏移量，也就是与GMT相差的微秒数，即TimeZone表示时区偏移量，本质上以毫秒数保存与GMT的差值。获取TimeZone可以通过时区ID，如"America/New_York"，
         也可以通过GMT+/-hh:mm来设定。例如北京时间可以表示为GMT+8:00;影响TimeZone的因素  1. 操作系统的时区设置。  2. 数据传输时时区设置。第一个原因其实是根本原因，当数据在不同操作系统间流转时，
         就有可能因为操作系统的差异造成时间偏差，而JVM默认情况下获取的就是操作系统的时区设置。因此在项目中最好事先设置好时区
     * 5  在时区间转换时间时，首先用原时间减掉原时间所在时区相对于GMT的偏移量，得到原时间相对于GMT的值，然后再加上目标时区相对GMT的偏移量即可。需要注意的是这样得到的结果依然是毫秒数，
 *       所以我们要按照指定日期格式重新转换成Date对象即可
     *
     * */

    public static void fun1(){
        //日期生成时默认是操作系统时区东八区
        Date date=new Date(1557983023000L);//2019-05-16 13:03:43 new以后为东8区
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));

        //日期生成时默认是操作系统时区东八区，计算0时区组件会-掉8小时
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf2.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println(sdf2.format(date));
    }

    /**
     *  将long转为时间格式、指定时区
     * */
    public static void fun2(){
        //将long转为时间格式，日期生成时默认是操作系统时区东八区，计算0时区组件会-掉8小时
        Date date=new Date(1391174450000L); // 2014-1-31 21:20:50 为东8区
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStrTmp = sdf.format(date);
        System.out.println(dateStrTmp);//2014-01-31 21:20:50

        //我的操作系统 是"Asia/Shanghai"，即GMT+8的北京时间，那么执行日期转字符串的format方法时，由于日期生成时默认是操作系统时区，因此 2014-1-31 21:20:50是北京时间
        //那么推算到GMT时区，自然是要减8个小时的,即结果（2014-01-31 13:20:50）
        //将时间转成0时区,计算GMT时间会减去8小时
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf2.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateStrTmp2 = sdf2.format(date);
        System.out.println(dateStrTmp2);//2014-01-31 13:20:50


    }

    public static void fun3(){

        try {
            String dateStr = "2014-1-31 21:20:50";//字符串本身没有时区的概念
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateTmp2 = sdf2.parse(dateStr);//parse以后认为输出的是服务器时区：GMT+8
            System.out.println(dateTmp2);


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date dateTmp = sdf.parse(dateStr);//设置了时区以后，组件内认为dateStr为0时区时间；  parse后输出的是服务器时区GMT+8时间，所以会+8小时
            System.out.println(dateTmp);

            //注意： 字符串转日期的parse方法输出的始终是当前服务器所在时区的时间
        }catch (Exception ex){

        }
    }

    public static void fun4(){

        //服务器所在时区转成0时区
        Date date = new Date(1391174450000L); // 2014-1-31 21:20:50
        System.out.println("默认的东八区时间："+date); //系统时区 东8
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        calendar.setTime(date);
        //设置完时区后，我们不能用calendar.getTime()来直接获取Date日期，因为此时的日期与一开始setTime时是相同值，要想获取某时区的时间，正确的做法是用calendar.get()方法
        //转成0时区-8小时
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));

        //服务器所在时区转成0时区，转换成date
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND));
        System.out.println("将默认的东八区转为GMT，模拟GMT时间:"+calendar2.getTime());
    }



    /***********************************************************************
     * 上面两个转换方法都要受到操作系统的时区设置影响new Date时始终都是服务器时区时间，如果软件在不同操作系统运行，仍然会有时间误差
     ***********************************************************************/

    /**
     * 将一个时区时间转为另外一个时区时间，东八->东九
     * 在时区间转换时间时，首先用原时间减掉原时间所在时区相对于GMT的偏移量，得到原时间相对于GMT的值，然后再加上目标时区相对GMT的偏移量即可。
     * 需要注意的是这样得到的结果依然是毫秒数，所以我们要按照指定日期格式重新转换成Date对象即可
     * */
    public static void fun5(){
        String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
        //DateFormat是日期/时间格式化子类的抽象类，它以与语言无关的方式格式化并解析日期或时间。日期/时间格式化子类（如 SimpleDateFormat）允许进行格式化（也就是日期 -> 文本）
        // 、解析（文本-> 日期）和标准化。将日期表示为 Date 对象，或者表示为从 GMT（格林尼治标准时间）1970 年 1 月 1 日 00:00:00 这一刻开始的毫秒数
        //SimpleDateFormat则是一个以与语言环境有关的方式来格式化和解析日期的具体类，可以以“日期和时间模式”字符串指定日期和时间格式。
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

        //new Date默认是系统所在地区时区即东八区： 2014-1-31 21:20:50

        Date date = new Date(1391174450000L);
        System.out.println("GMT+9: " + date);
        //模拟东8区时间需要将时间指定为东8区
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        calendar.setTime(date);

        //东九区时间减掉时间偏移量 得到0时区时间
        Long gmttime= calendar.getTimeInMillis()-calendar.getTimeZone().getRawOffset();
        System.out.println("GMT0："+formatter.format(new Date(gmttime)));

        //计算东九区时间
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeZone(TimeZone.getTimeZone("GMT+9"));
        Long targetTime=gmttime + calendar2.getTimeZone().getRawOffset();
        System.out.println("GMT+9："+formatter.format(new Date(targetTime)));


    }


    public static Calendar StringToCalendar(){
        try {
            //string 转 date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = sdf.parse("2018-05-06 10：20：08");
            //date 转 Calendar
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        }catch (Exception ex){


        }
        return  null;
    }




    /**
     * 解决夏令时问题，将时区设置成区域形式而非时区偏移量形式
     * */
    public static void xialingshi(){

        Date date = new Date();
        System.out.println("默认的东八区时间："+date); //系统时区 东8
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //如果此处指定timezone为标准时区格式：
        // 例如美国纽约 是西5区，timezone为GMT-5  未考虑夏令时情况下则计算得到的会比实际当地时间早1个小时
        // 我们通过将timezone设置成 区域格式: America/New_York  则转换后出来的时间是准确的当地时间
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));   //America/New_York
        System.out.println("New_York时间："+sdf.format(date));



        /*Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        calendar.setTime(date);
        sdf.parse(calendar)*/

    }


    public static void main(String[] args) {
        System.out.println(TimeZone.getTimeZone("Asia/Bangkok").getRawOffset());

        xialingshi();
    }
}
