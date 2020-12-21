package com.shuailee.adapter.login;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-04 21:58
 **/
public class SiginService {
    /**
     * 注册方法
     * @param username
     * @param password
     * @return
     */
    public ResultMsg regist(String username,String password){
        return new ResultMsg(200,"注册成功",new Member());
    }
    /**
     * 登录的方法
     * @param username
     * @param password
     * @return
     */
    public ResultMsg login(String username,String password){
        return null;
    }
}


class Member{

    private String uid;
    private String name;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}