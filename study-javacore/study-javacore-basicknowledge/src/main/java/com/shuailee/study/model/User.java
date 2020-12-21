package com.shuailee.study.model;

import lombok.Data;

import java.io.Serializable;


public class User implements Serializable {


    private Integer user_id;

    private String user_name;

    private String user_address;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public User(Integer user_id, String user_name, String user_address) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_address = user_address;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_address='" + user_address + '\'' +
                '}';
    }
}
