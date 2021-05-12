package com.shuailee.study.tuomin;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.util.Objects;

/**
 * @package: com.shuailee.study.tuomin
 * @description:
 * @author: klein
 * @date: 2021-05-12 19:43
 *
 * http://www.scienjus.com/get-field-annotation-property-by-jackson-contextualserializer/
 * https://www.jianshu.com/p/90d7d101e715
 * https://blog.csdn.net/u013089490/article/details/83585794
 * https://blog.csdn.net/liufei198613/article/details/79009015
 * http://www.voidcn.com/article/p-ezjgnfeh-bee.html
 *
 *
 **/
public class SensitiveInfoSerialize extends JsonSerializer<String> implements ContextualSerializer {
    private SensitiveType type;

    public SensitiveInfoSerialize() {
    }

    public SensitiveInfoSerialize(final SensitiveType type) {
        this.type = type;
    }


    /**
     * 根据注解传入的值对字段进行不同的脱敏策略
     * JsonSerializer类的serialize方法是序列化字段时的处理方法，它内部不能获得字段注解等元数据信息
     * 如果要获取元数据信息需要实现ContextualSerializer接口的createContextual方法     *
     * */
    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        switch (this.type) {
            case CHINESE_NAME: {
                // 为字段赋值
                jsonGenerator.writeString(SensitiveInfoUtils.chineseName(s));
                break;
            }
            case ID_CARD: {
                jsonGenerator.writeString(SensitiveInfoUtils.idCardNum(s));
                break;
            }
            case FIXED_PHONE: {
                jsonGenerator.writeString(SensitiveInfoUtils.fixedPhone(s));
                break;
            }
            case MOBILE_PHONE: {
                jsonGenerator.writeString(SensitiveInfoUtils.mobilePhone(s));
                break;
            }
            case ADDRESS: {
                jsonGenerator.writeString(SensitiveInfoUtils.address(s, 4));
                break;
            }
            case EMAIL: {
                jsonGenerator.writeString(SensitiveInfoUtils.email(s));
                break;
            }
            case BANK_CARD: {
                jsonGenerator.writeString(SensitiveInfoUtils.bankCard(s));
                break;
            }
            case CNAPS_CODE: {
                jsonGenerator.writeString(SensitiveInfoUtils.cnapsCode(s));
                break;
            }
//            default:{
//                jsonGenerator.writeString(s);
//                break;
//            }
        }

    }

    /**
     * createContextual可以获得字段的类型以及注解。
     * 当字段为String类型并且拥有@SensitiveInfo注解时，取出注解中的值创建定制的SensitiveInfoSerialize（当前类），这样在serialize方法中便可以得到这个值了
     * createContextual方法只会在第一次序列化字段时调用（因为字段的上下文信息在运行期不会改变），所以不用担心影响性能
     * */
    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (null == beanProperty) {
            return serializerProvider.findNullValueSerializer(null);
        }
        // 必须是String类型
        if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
            // 获取注解
            SensitiveInfo sensitiveInfo = beanProperty.getAnnotation(SensitiveInfo.class);
            if (sensitiveInfo == null) {
                sensitiveInfo = beanProperty.getContextAnnotation(SensitiveInfo.class);
            }
            // 如果能得到注解，就将注解的 value 传入 SensitiveInfoSerialize
            if (sensitiveInfo != null) {
                return new SensitiveInfoSerialize(sensitiveInfo.value());
            }

        }
        return serializerProvider.findNullValueSerializer(null);
    }
}
