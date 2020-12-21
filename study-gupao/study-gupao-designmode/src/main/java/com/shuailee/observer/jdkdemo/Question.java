package com.shuailee.observer.jdkdemo;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-05 22:27
 **/
public class Question {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String userName;
    private String content;
}
