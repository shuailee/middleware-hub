package com.shuailee.study.mapstructdemo.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @package: com.shuailee.study.mapstructdemo
 * @description:
 * @author: klein
 * @date: 2021-05-12 10:21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    String className;
    String classHours;
}
