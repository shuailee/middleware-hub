package com.shuailee.study.ut.powermock;

import com.shuailee.study.ut.demo.User;

/**
 * @program: study-javacore
 * @description:
 * @create: 2019-08-06 22:14
 **/
public class CustomerProcess {

    public boolean save(Long uid, User user){

        boolean norepeater=checkRepeater(uid);
        if(norepeater) {
            if (uid != null && user != null) {
                System.out.println("持久化到数据库！");
                return true;
            } else {
                System.out.println("数据缺失！");
            }
        }else {
            System.out.println("已存在"+uid);
        }
        return false;
    }

    // 希望被mock的方法
    private boolean checkRepeater(Long uid){
        System.out.println("检测是否已存在:"+uid);
        if(uid>0){
            return true;
        }
        return false;
    }
}
