package com.shuailee.study.basicknowledge;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: study-javacore
 * @description:
 * @create: 2019-07-10 15:38
 **/
public class Test {

    static HashMap<String , Student> map=new HashMap<>();
    public static void main(String[] args) {


        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("abc");

        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(123);

        System.out.println(list1.getClass() == list2.getClass());



     /*   new Thread(() -> {
            int i=0;
            while (true) {
                i++;
                map.put("test"+i,new Student(i,"testvvv"));
                System.out.println("hello" + Thread.currentThread().getName()+"i:"+i);
            }
        }).start();*/


    }


}


class Student{
    Integer age;
    String name;

    public Student(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}