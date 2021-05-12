package com.shuailee.study.mapstructdemo.model;

import com.shuailee.study.mapstructdemo.StudentMapper;
import com.shuailee.study.mapstructdemo.model.dao.Address;
import com.shuailee.study.mapstructdemo.model.dao.Country;
import com.shuailee.study.mapstructdemo.model.dao.Course;
import com.shuailee.study.mapstructdemo.model.dao.Student;
import com.shuailee.study.mapstructdemo.model.dto.StudentDTO;

import java.util.ArrayList;

/**
 * @package: com.shuailee.study.mapstructdemo.model
 * @description:
 * @author: klein
 * @date: 2021-05-12 10:36
 **/
public class MapStructMain {
    public static void main(String[] args) {
        Student student = build();
        System.out.println("转换前：" +student.toString());

        StudentDTO studentDTO = StudentMapper.INSTANCE.studentTostudentDto(student);

        System.out.println("转换后：" +studentDTO.toString());
    }

    public static Student build() {
        return new Student("jerry", "18", "131111111", new ArrayList<Course>() {{
            add(new Course("语文", "12"));
            add(new Course("英语", "24"));
            add(new Course("数学", "18"));
        }}, new Address("sh", "beijing", new Country("china", "86")));
    }


}
