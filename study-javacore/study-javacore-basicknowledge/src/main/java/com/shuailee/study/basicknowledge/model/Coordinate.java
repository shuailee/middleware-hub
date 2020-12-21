package com.shuailee.study.basicknowledge.model;

/**
 * @program: study-javacore
 * @description:
 * @author: shuai.li
 * @create: 2019-03-14 20:51
 **/
public class Coordinate{
    /**
     * 经度
     * */
    private String longitude;
    /**
     * 纬度
     * */
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}