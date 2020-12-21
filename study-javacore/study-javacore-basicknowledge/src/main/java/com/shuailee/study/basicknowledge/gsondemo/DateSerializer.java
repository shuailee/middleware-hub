package com.shuailee.study.basicknowledge.gsondemo;

import com.google.gson.*;

import javax.xml.crypto.Data;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * @program: study-javacore
 * @description: 自定义Gson类型转换
 * @author: shuai.li
 * @create: 2020-07-02 20:35
 **/
public class DateSerializer  {

    // 注册类型适配器
    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Data.class, new DateAdaptertype())
                .create();
    }
}

/**
* 自定义类型适配器需要实现两个接口：JsonSerializer<T>,JsonDeserializer<T>
* */
class DateAdaptertype implements JsonSerializer<String>,JsonDeserializer<Date>{
    @Override
    public Date deserialize(JsonElement jsonElement, Type type,
                            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if(jsonElement == null){
            return null;
        }
        return new Date();
    }

    @Override
    public JsonElement serialize(String date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(date);
    }
}

