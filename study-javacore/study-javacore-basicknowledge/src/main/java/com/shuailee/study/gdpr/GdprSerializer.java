package com.shuailee.study.gdpr;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;

/**
 * 继承JsonSerializer 自定义序列化器，实现ContextualSerializer 接口可以对元数据进行访问
 * */
public class GdprSerializer extends JsonSerializer<Object> implements ContextualSerializer {
    private GdprField gdprField;
//    private JsonSerializer<?> serializer;
//
//    public GdprSerializer(JsonSerializer<?> serializer) {
//        this.serializer = serializer;
//    }

    public GdprSerializer() {

    }
    public GdprSerializer(GdprField gdprField) {

        this.gdprField = gdprField;
    }
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value.getClass() == String.class) {
            String strValue = value.toString();
            if (null != this.gdprField) {
                String hashValue = strValue + "*****";
                gen.writeString(hashValue);
            } else {
                gen.writeString(strValue);
            }
        }
    }
    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        if (null == property) {
            return prov.findNullValueSerializer(null);
        }
        GdprField gdprField = property.getAnnotation(GdprField.class);

        if (gdprField != null) {
            return new GdprSerializer(gdprField);
        }
        return prov.findNullValueSerializer(null);
    }
}