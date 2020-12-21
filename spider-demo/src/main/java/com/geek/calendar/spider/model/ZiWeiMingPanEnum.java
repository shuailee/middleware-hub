package com.geek.calendar.spider.model;

/**
 * @program: geek-calendar-spider
 * @description: ZiWeiMingPanEnum
 * @author: shuai.li
 * @create: 2020-04-15 15:14
 **/
public enum ZiWeiMingPanEnum {
    NUPU_GONG(1, "奴仆宫"),
    QIANYI_GONG(2, "迁移宫"),
    JIE_GONG(3, "疾厄宫"),
    CAIBO_GONG(4, "财帛宫"),
    SHIYE_GONG(5, "事业宫"),
    ZINV_GONG(6, "子女宫"),
    TIANZHAI_GONG(7, "田宅宫"),
    FUQI_GONG(8, "夫妻宫"),
    FUDE_GONG(9, "福德宫"),
    FUMU_GONG(10, "父母宫"),
    MINGGONG_GONG(11, "命宫"),
    XIONGDI_GONG(12, "兄弟宫");


    private int code;
    private String value;

    private ZiWeiMingPanEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }

    public static boolean equalsName(String name){
        return NUPU_GONG.name().equals(name);
    }
}
