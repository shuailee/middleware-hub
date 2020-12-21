package com.shuailee.study.cglibdemo;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: study-javacore
 * @description:
 * @create: 2019-07-18 14:41
 **/
public class CglibTest {
    public static void main(String[] args) {

        System.out.println("************cglib BeanCopier实现深拷贝***********");
        deepCopy();
        System.out.println("************cglib BeanCopier实现不同对象map（自定义转换器）***********");
        dtoMap();

    }

    private static void dtoMap()
    {
        /**
         * BeanCopier 支持相同类型或者不同类型对象之间的数据copy，主需要保证成员属性名相同即可
         * 不同对象类型需要指定create的第三个参数为true，并在执行copy方法时指定自定义转换器，
         * 例如 Date time,String time, long time.这三种，在转换的时候如何处理。就需要自己写方法对相应的类型进行修改.
         * */
        StudentDto studentDto=new StudentDto("小李",18,2000,1565424509000L);
        System.out.println(studentDto.toString());
        Student student=new Student ();
        BeanCopier copier=BeanCopier.create(studentDto.getClass(),student.getClass(),true);
        copier.copy(studentDto,student,new DateConverter());
        System.out.println(student.toString());
    }

    /**
     * 使用cglib的 BeanCopier类实现深拷贝
     * */
    private static void deepCopy() {
        Student student=new Student("小李",new Age(18),2000,"2019-8-10");
        System.out.println(student.toString());
        Student student2=new Student ();
        BeanCopier copier=BeanCopier.create(student.getClass(),student2.getClass(),false);
        copier.copy(student,student2,null);
        System.out.println(student2.toString());
    }

}

/**
 * 自定义转换器
 * */
class DateConverter implements Converter{

    /**
     * 参数1 当前要转换字段的值
     * 参数2 要转成的目标类型
     * 参数3 要执行的set方法名
     * 如果都不符合则执行默认赋值
     * */
    @Override
    public Object convert(Object sourceVlue, Class targetClass, Object o1) {
        // a.isAssignableFrom(b), 查资料:即a是b的父类或接口 || a和b为同一个类或同一个接口 则返回true.
        if(sourceVlue.getClass().isAssignableFrom(Long.class)){
            Date date=new Date((Long) sourceVlue);
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        }
        if(targetClass.equals(Age.class) ){
            return new Age((int)sourceVlue);
        }
        return sourceVlue;

    }
}



class Student{
    //学生类的成员变量（属性）,其中一个属性为类的对象
    private String name;
    private Age age;
    private int length;
    private String birthday;

    public Student(){}
    //构造方法,其中一个参数为另一个类的对象
    public Student(String name,Age age,int length,String birthday) {
        this.name=name;
        this.age=age;
        this.length=length;
        this.birthday=birthday;
    }
    //eclipe中alt+shift+s自动添加所有的set和get方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Age getAge() {
        return this.age;
    }

    public void setAge(Age age) {
        this.age=age;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length=length;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", length=" + length +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}

class Age{
    //年龄类的成员变量（属性）
    private int age;
    //构造方法
    public Age(int age) {
        this.age=age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return this.age+"";
    }
}


class StudentDto{
    //学生类的成员变量（属性）,其中一个属性为类的对象
    private String name;
    private int age;
    private int length;
    private Long birthday;

    public StudentDto(String name, int age, int length, Long birthday) {
        this.name = name;
        this.age = age;
        this.length = length;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", length=" + length +
                ", birthday=" + birthday +
                '}';
    }
}


