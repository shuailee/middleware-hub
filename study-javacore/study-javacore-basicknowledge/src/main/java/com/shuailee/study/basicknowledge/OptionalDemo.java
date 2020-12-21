package com.shuailee.study.basicknowledge;

import com.shuailee.study.basicknowledge.model.Airport;
import com.shuailee.study.basicknowledge.model.Coordinate;
import com.shuailee.study.basicknowledge.model.Flight;

import java.util.Optional;

/**
 * @program: study-javacore
 * @description:
 * @author: shuai.li
 * @create: 2019-03-04 16:23
 **/
public class OptionalDemo {

    public static void main(String[] args) {
        System.out.println(getLongitude(getFlight()));
        System.out.println(getLongitude(null));
        showFlightno(getFlight());
    }

    public static Flight getFlight()
    {
        Coordinate coordinate=new Coordinate();
        coordinate.setLatitude("11111111");
        coordinate.setLongitude("22222222");
        Airport airport=new Airport();
        airport.setCode("1");
        airport.setName("浦东国际机场");
        airport.setCoordinate(coordinate);
        Flight flight=new Flight();
        flight.setDepartureAirport(airport);
        return flight;
    }

    public static String getLongitude(Flight flight){
        return Optional.ofNullable(flight)
                .map(a->a.getDepartureAirport())
                .map(c -> c.getCoordinate())
                .map(d->d.getLongitude())
                .orElse("empty");

    }

    public static void showFlightno(Flight flight){

         Optional.ofNullable(flight).ifPresent(s-> System.out.println(s.getFlightNo()));

    }

}
