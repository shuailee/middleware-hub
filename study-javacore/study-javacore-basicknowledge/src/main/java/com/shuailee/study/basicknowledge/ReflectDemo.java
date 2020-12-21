package com.shuailee.study.basicknowledge;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

import java.lang.reflect.*;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: study-javacore
 * @description: 反射demo
 * @author: shuai.li
 * @create: 2018-06-28 11:30
 **/
public class ReflectDemo {



    public static void main(String [] agrs){
        //demo1();
        //demo2();

        // 利用反射获取对象的 指定字段和字段的值
        Gum gum=new Gum();
        gum.setName("普罗米修斯");
        gum.setFenshu(8800);
        func(gum);
    }

    /**
     * 通过反射构造对象
     * */
    public static void demo1()
    {

        try {
            System.out.println("-------------------使用反射加载类并创建对象实例-------------------------");

            //Class.forName()方法的调用将会返回一个对应类的Class对象，且会初始化静态块
            Class<?> clazz= Class.forName("com.shuailee.study.basicknowledge.Gum");

            //实例化默认构造方法
            Gum gum=(Gum)clazz.newInstance();
            gum.setName("普罗米修斯");
            gum.setFenshu(8800);
            System.out.println("gum1:"+gum);

            //实例化带参数的构造函数
            Constructor cs1= clazz.getConstructor(int.class);
            Gum gum2=(Gum)cs1.newInstance(600);
            System.out.println("gum2:"+gum2.toString());

            //实例化带有两个参数的构造函数
            Constructor cs2= clazz.getConstructor(int.class,String.class);
            Gum gum3=(Gum)cs2.newInstance(600,"阿波罗");
            System.out.println("gum3:"+gum3.toString());
            gum3.sayName(true);

            System.out.println("--------------------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射获取成员信息
     * */
    public static void demo2() {
        try {

            System.out.println("\n\n-------------------使用反射获取类的成员-------------------------");
            System.out.println("\n\n##########获取所有构造函数##########");
            //返回一个对应类的Class对象 且会初始化静态块
            Class<?> clazz = Class.forName("com.shuailee.study.basicknowledge.Gum");
            //获取所有构造函数 包含private
            Constructor cons[]=clazz.getDeclaredConstructors();
            for (int i = 0; i < cons.length; i++) {
                System.out.println("构造函数["+i+"]:"+cons[i].toString() );
                //获取所有构造函数形参类型
                Class<?>[] parstype= cons[i].getParameterTypes();
                System.out.print("参数类型["+i+"]:(");
                for (int j = 0; j < parstype.length; j++) {
                    if(j==parstype.length-1) {
                        System.out.print(parstype[j].getName());
                    }else {
                        System.out.print(parstype[j].getName()+"，");
                    }
                }
                System.out.println(")");
            }


            System.out.println("\n\n##########获取所有方法##########");
            //获取所有公共方法
            Method[] mt= clazz.getMethods();
            for(int i=0;i<mt.length;i++){
                StringBuilder sb=new StringBuilder();
                sb.append("方法名："+mt[i].getName());
                //获取所有方法形参类型
                Class<?> [] mp= mt[i].getParameterTypes();
                sb.append("参数类型["+i+"]:(");
                for (int j=0;j<mp.length;j++){
                    if(j==mp.length-1) {
                        sb.append(mp[j].getName());
                    }else {
                        sb.append(mp[j].getName()+"，");
                    }
                }
                sb.append(")");
                System.out.println(sb.toString());
            }

            //根据方法签名获取指定方法
            Method method=clazz.getMethod("sayName",Boolean.class);
            System.out.println("sayName："+method);


            System.out.println("\n\n##########获取所有字段##########");
            // //获取当前类所字段(包含private字段),注意不包含父类的字段
            for (Field f:clazz.getDeclaredFields()) {
            System.out.println("fieldName:"+f.getName()+" type:"+f.getType());
            }


            System.out.println("--------------------------------------------------------------");

            /**
             * 获取参数名具有重要意义：例如sping框架中自动识别参数传值，mybatis中字段赋值也有应用
             *
             *jdk1.8之前需要解析字节码的方式获取参数名（可以认为java不支持获取参数名），1.8之后可以通过反射获取参数名了
             *jdk1.8 通过反射获取方法的参数名,需要开启设置：
             * 在.File->Settings->Build,Execution,Deployment->Compiler->Java Compiler下
             * Additional command line parameters: 后面填上 -parameters
             * 否则获取到的是arg0,arg1..
             *
             * Parameter对象是新的反射对象，param.isNamePresent()表示是否编译了参数名,开启配置以后返回true否则为false
             * */
            System.out.println("\n\n***********************通过反射获取方法参数名和参数类型************************");
            //获取所有方法
            Method[] methods= clazz.getDeclaredMethods();
            for(int i=0;i<methods.length;i++){
                StringBuilder sb=new StringBuilder();
                sb.append("方法："+methods[i].getName()+"(");
                //获取方法的参数信息jdk1.8之后
                Parameter [] parameters=methods[i].getParameters();
                for (Parameter parameter:parameters) {
                    //是否编译了参数名
                    if(parameter.isNamePresent()){
                        sb.append(parameter.getType().getSimpleName()+" " + parameter.getName() + ",");
                    }
                }
                sb.append(");");
                System.out.println(sb.toString().replace(",);",");"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过反射读取和为字段赋值
     * 通过反射发起方法调用
     * */
    public static  <T> void func(T t) {
        try {
            //利用反射获取对象的 指定字段和字段的值
            //字段如果是私有的 需要使用getDeclaredFields   字段如果是pubulic 则直接使用getField
            Field[] fields = t.getClass().getDeclaredFields();
            List<Field> fieldList=Arrays.asList(fields);
            for (Field field:fieldList) {
                if(field.getName().equalsIgnoreCase("name")){
                    // 强制设置字段可见，就可以通过反射读取和设置字段的值了
                    field.setAccessible( true );
                    System.out.println(field.getName()+":"+field.get(t));
                    //通过反射为字段重新赋值
                    //第一个参数为当前对象，第二个参数为字段的值
                    field.set(t,"阿尔萨斯");
                    System.out.println(field.getName()+"字段值被修改为："+field.get(t));
                }
            }

            System.out.println("**********************通过反射发起方法调用******************************");
            //通过反射发起方法调用，输出字段值为反射更新后最新的值
            Method [] methods= t.getClass().getMethods();
            for (Method method:methods) {
                if("sayname".equals(method.getName().toLowerCase())){
                    //第一个参数为当前对象，第二个参数为方法的实参
                    method.invoke(t,new Object[]{true});
                }

            }

        }catch (Exception ex){

        }
    }


}



@Data
class Gum
{
    private int fenshu;
    private String name;
    /**
     * desc: Gum无参构造函数
     * @parameter: []
     * @return:
     */
    public Gum(){
    }
    /**
     * desc: Gum有参构造函数
     * @parameter: [f]
     * @return:
     */
    public Gum(int f){

        this.fenshu=f;
    }
    /**
     * desc: Gum有参构造函数
     * @parameter: [f]
     * @parameter: [n]
     * @return:
     */
    public Gum(int f,String n){
        this.name=n;
        this.fenshu=f;
    }



    /**
     * propety
     * */
    public int getFenshu() {
        return fenshu;
    }
    /**
     * propety
     * */
    public void setFenshu(int fenshu) {
        this.fenshu = fenshu;
    }
    /**
     * propety
     * */
    public String getName() {
        return name;
    }
    /**
     * propety
     * */
    public void setName(String name) {
        this.name = name;
    }




    /**
     * say method
     * */
    public void say()
    {
        System.out.println("hello wold");
    }

    /**
     * sayName method
     * */
    public void sayName(Boolean all)
    {
        if(all){
            System.out.println(MessageFormat.format("sayName方法被调用参数为：{0}\n输出内容：fenshu:{1},  name：{2}",all,this.fenshu,this.name));
        }else {
            System.out.println("sayName方法被调用,参数为："+all);
        }

    }
    /**
     * syasex method
     * */
    private void syasex()
    {
        System.out.println("wm");
    }

}
