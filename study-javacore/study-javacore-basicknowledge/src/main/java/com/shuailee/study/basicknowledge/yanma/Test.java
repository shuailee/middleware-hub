package com.shuailee.study.basicknowledge.yanma;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static cn.hutool.core.util.RandomUtil.randomLong;
import static com.shuailee.study.basicknowledge.yanma.VirtualMobileProcessor.cryptoMobile;
import static com.shuailee.study.basicknowledge.yanma.VirtualMobileProcessor.cryptoMobileForList;

/**
 * @package: com.shuailee.study.basicknowledge.yanma
 * @description:
 * @author: klein
 * @date: 2021-05-11 19:33
 **/
public class Test {
    public static void main(String[] args) {
        //单对象测试
        System.out.println(cryptoMobile(new Student(randomLong(11111111L, 99999999L), String.valueOf(randomLong(13011111111L, 13099999999L)),
                1, LocalDateTime.now()), Student.class));
        //集合测试
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setId(randomLong(11111111L, 99999999L));
            student.setMobile(String.valueOf(randomLong(13011111111L, 13099999999L)));
            student.setCryptoStatus(1);
            student.setCreateTime(LocalDateTime.now());
            studentList.add(student);
        }
        long startMillis = System.currentTimeMillis();
        Collection<Student> students = cryptoMobileForList(studentList, Student.class);
        long stopMillis = System.currentTimeMillis();
        System.out.println(stopMillis - startMillis + "ms");
        System.out.println(students);
    }

}
