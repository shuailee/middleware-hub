package com.shuailee.study.mapstructdemo.model.dto;

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
public class StudentDTO {
    String name;
    String age;
    String tel;
    List<CourseDTO> courseList;
    AddressDTO address;

    @Override
    public String toString() {
        return "StudentDTO{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", tel='" + tel + '\'' +
                ", courseList=" + courseList +
                ", address=" + address +
                '}';
    }
}
