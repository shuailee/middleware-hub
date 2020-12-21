package com.shuailee.PrototypePattern.deeptest;

import java.io.Serializable;

public class School implements Serializable {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    School(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
