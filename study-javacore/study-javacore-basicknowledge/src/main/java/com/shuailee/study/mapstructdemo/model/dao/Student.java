package com.shuailee.study.mapstructdemo.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @package: com.shuailee.study.mapstructdemo
 * @description:
 * @author: klein
 * @date: 2021-05-12 10:20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    String name;
    String age;
    String tel;
    List<Course> courseList;
    Address address;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", tel='" + tel + '\'' +
                ", courseList=" + courseList +
                ", address=" + address +
                '}';
    }
}
