package com.shuailee.study.basicknowledge.model;

import lombok.Data;

/**
 * @program: study-javacore
 * @description: 机场信息
 * @author: shuai.li
 * @create: 2018-06-26 18:32
 **/
@Data
public class Airport {
    private int id;
    /*
    * 机场名称
    * */
    private String name;
    /*
    * 机场编号
    * */
    private String code;

    /**
     * 经纬度
     * */
    private Coordinate coordinate;


    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}



