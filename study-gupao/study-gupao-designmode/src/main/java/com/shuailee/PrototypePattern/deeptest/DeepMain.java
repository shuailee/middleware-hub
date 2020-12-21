package com.shuailee.PrototypePattern.deeptest;

import java.io.IOException;

public class DeepMain {
    public static void main(String [] args){


        Student s1=new Student("a",1,new School("donghua","28"));

        //浅拷贝
        Student s3=(Student)s1.clone();
        //改变s3的学校地址
        s3.getP().setAddress("东方路10号");
        System.out.println("s3是s1的浅拷贝副本，修改引用类型内成员的值后，他们是否相等："+s1.getP().getAddress().equals(s3.getP().getAddress()));


        //深拷贝
        try {
            Student s2=(Student)s1.deepClone();

            //改变s2的学校地址
            s2.getP().setAddress("长安街310号");
            System.out.println("s2是s1的浅拷贝副本，修改引用类型内成员的值后，他们是否相等："+s1.getP().getAddress().equals(s2.getP().getAddress()));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }







    }
}
