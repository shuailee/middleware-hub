package com.shuailee.model;

import java.io.Serializable;

/**
 * 与数据库字段不匹配的命名可以通过三种方式解决：
 * 1 配置中开启数据库字段到javabean 对象的默认推断
 * 2 sql中的as
 * 3 如果开启了MyBatis的二级缓存，则需要将javabean 实现Serializable接口
 * */
public class UserModel implements Serializable {
    //用户id
    private int userId;
    //姓名
    private String userName;
    //性别
    private String userSex;
    //出生日期
    private String userBirthday;
    //邮箱Ｅ
    private String userEmail;
    //学历
    private String userEdu;
    //联系方式
    private String userTelephone;
    //住址
    private String userAddress;

    public UserModel(String userName, String userSex, String userBirthday, String userEmail, String userEdu, String userTelephone, String userAddress) {
        this.userName = userName;
        this.userSex = userSex;
        this.userBirthday = userBirthday;
        this.userEmail = userEmail;
        this.userEdu = userEdu;
        this.userTelephone = userTelephone;
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userBirthday='" + userBirthday + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userEdu='" + userEdu + '\'' +
                ", userTelephone='" + userTelephone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEdu() {
        return userEdu;
    }

    public void setUserEdu(String userEdu) {
        this.userEdu = userEdu;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
