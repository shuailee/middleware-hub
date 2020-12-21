package com.shuailee.study.basicknowledge.model;

import lombok.Data;

import java.util.Calendar;
import java.util.List;

/**
 * @program: study-javacore
 * @description:
 * @author: shuai.li
 * @create: 2018-06-26 12:22
 **/
@Data
public class Flight {
    /**
    * 航班号
    * */
    private String flightNo;

    /**
     * 承运航司
     * */
    private String operatingCarrier;
    /**
    * 飞行时长
    * */
    private int duration;
    /**
     * 机型
     * */
    private String airplaneType;
    /**
    * 起飞时间
    * */
    private Calendar  departuretime;

    /**
     * 到达时间
     * */
    private Calendar arrivalTime;

    /**
     * 经停信息
     * */
    private List<String> stopInfo;

    /**
    * 起飞机场
    * */
    private Airport departureAirport;

    /**
    * 到达机场
    * */
    private Airport arrivalAirport;

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getOperatingCarrier() {
        return operatingCarrier;
    }

    public void setOperatingCarrier(String operatingCarrier) {
        this.operatingCarrier = operatingCarrier;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public Calendar getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(Calendar departuretime) {
        this.departuretime = departuretime;
    }

    public Calendar getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Calendar arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<String> getStopInfo() {
        return stopInfo;
    }

    public void setStopInfo(List<String> stopInfo) {
        this.stopInfo = stopInfo;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
}
