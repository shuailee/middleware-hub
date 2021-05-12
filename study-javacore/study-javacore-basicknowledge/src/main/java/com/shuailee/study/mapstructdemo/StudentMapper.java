package com.shuailee.study.mapstructdemo;

import com.shuailee.study.mapstructdemo.model.dao.Course;
import com.shuailee.study.mapstructdemo.model.dao.Student;
import com.shuailee.study.mapstructdemo.model.dto.CourseDTO;
import com.shuailee.study.mapstructdemo.model.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.StringJoiner;

/**
 * @package: com.shuailee.study.mapstructdemo
 * @description:
 * @author: klein
 * @date: 2021-05-12 10:51
 **/
@Mapper
public interface StudentMapper {

    // https://www.cnblogs.com/DDgougou/articles/13365277.html

    StudentMapper INSTANCE = Mappers.getMapper( StudentMapper.class );
    /**
     * 映射子类中的属性，属性名.属性名.字段名
     * */
    @Mapping(source = "address.country.code", target = "address.country.areaCode")
    StudentDTO studentTostudentDto(Student student);
    /**
     * 在执行上面的map的时候，如果遇到Student类里有列表属性 list<Course> ，会自动遍历执行下面这个map
     * 注意，这只是在需要映射列表中子对象字段的时候才需要再写下面这个map，如果字段名称都一样则不需要在写下面子对象的map
     * */
    @Mappings({
            @Mapping(source = "className", target = "clazzName"),
            // qualifiedByName：根据自定义的方法进行赋值;
            @Mapping(source = "classHours", target = "classHours",qualifiedByName = {"classHoursFormat"})
    })
    CourseDTO courseToCourseDTO(Course course);

    @Named("classHoursFormat")
    default String classHoursFormat(String classHours){
        return new StringJoiner("_").add(classHours).add("h").toString();
    }
}
