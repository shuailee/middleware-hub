package com.shuailee.study.ut.demo;

import com.shuailee.study.basicknowledge.StringUtil;
import org.junit.platform.commons.util.StringUtils;

import java.util.logging.Logger;

/**
 * @program: study-javacore
 * @description:
 * @create: 2019-08-01 17:42
 **/
public class MyService {
    private MyRepository myRepository;
    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public MyRepository getMyRepository() {
        return myRepository;
    }

    public void setMyRepository(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public boolean insert(Long id,String name) {
        if(StringUtils.isBlank(name) || id<=0 ){
            return false;
        }
        User user=new User(id,name);
        int i= this.myRepository.insert(user);
        if(i>0){
            System.out.println("sucess");
            return true;
        } else {
            System.out.println("fail");
            return false;
        }

    }

    public User findById(Long id) {
        return this.myRepository.findById(id);
    }
}
