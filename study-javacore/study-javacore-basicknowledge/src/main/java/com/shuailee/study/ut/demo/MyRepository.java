package com.shuailee.study.ut.demo;

/**
 * @program: study-javacore
 * @description:
 * @create: 2019-08-01 17:39
 **/
public class MyRepository {
    public int insert(User user) {
        System.out.println("here's dosomething"+ user.toString());
        return 1;
    }
    public User findById(Long id) {
        return new User(id, "Real Repository");
    }
}
