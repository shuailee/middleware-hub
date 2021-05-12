package com.shuailee.study.gdpr;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;


import java.io.IOException;
import java.util.Calendar;


/**
 * @Author: zhangrm
 * @Description:
 * @Date: Created in 2021/4/26
 * @Modified By:
 */
public class Main {

//    /**
//     * 自定义日期格式的序列化器
//     * */
//    final static class GdprCalendarSerializer extends CalendarSerializer {
//
//        @Override
//        public void serialize(Calendar value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
//            jgen.writeString(getTimeString(value));
//        }
//
//        private static String getTimeString(Calendar calendar) {
//            if (calendar == null) {
//                return null;
//            }
//            return "你想要的日期格式";
//        }
//    }

    public static void main(String[] args) throws JsonProcessingException {




        // 这三行代码 也可以使用在对象上打上 @JsonSerialize(using = GdprCalendarSerializer.class) 注解解决
        //设置日期格式的序列化器
//        SimpleModule simpleModule = new SimpleModule().addSerializer(new GdprCalendarSerializer());
//        //设置序列化方式
//        simpleModule.setSerializerModifier(new GdprBeinSerializerModifier());
//        objectMapper.registerModule(simpleModule);

        ObjectMapper objectMapper = new ObjectMapper();
        Cat cat = new Cat();
        cat.setName("tom");
        cat.setTel("123");
        //将POJO序列化成JSON，使用mapper的writeValueAsString方法：
        String val = objectMapper.writeValueAsString(cat);
        //使用readValue方法来反序列化上面的JSON字符串，即json转换为对象

        System.out.println(val);


    }
}
