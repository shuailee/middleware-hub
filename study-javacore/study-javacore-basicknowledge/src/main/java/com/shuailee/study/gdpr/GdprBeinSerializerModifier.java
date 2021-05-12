//package com.shuailee.study.gdpr;
//
//import com.fasterxml.jackson.databind.BeanDescription;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializationConfig;
//import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
//
//
///**
// * 自定义序列化修改器
// * https://www.cnblogs.com/scar1et/articles/14134024.html
// * */
//public class GdprBeinSerializerModifier extends BeanSerializerModifier {
//
//    @Override
//    public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc, JsonSerializer<?> serializer) {
//        if (beanDesc.getBeanClass() == String.class) {
//            // 当为String类型时 指定序列化器
//            return new GdprSerializer(serializer);
//        } else {
//            return super.modifySerializer(config, beanDesc, serializer);
//        }
//    }
//}
