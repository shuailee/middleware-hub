package com.shuailee.study.basicknowledge.gsondemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

/**
 * @program: study-javacore
 * @description: GsonDemo
 * @author: shuai.li
 * @create: 2020-07-02 20:27
 **/
public class GsonDemo {
    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateToLongAdapter());
        Gson gson = builder.create();



    }


}

class DateToLongAdapter extends TypeAdapter<Date> {

    @Override
    public void write(JsonWriter jsonWriter, Date date) throws IOException {
        if (date == null) {
            jsonWriter.value("");
            return;
        }
        jsonWriter.value(date.getTime());
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        return null;
    }
}





