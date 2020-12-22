package com.geek.calendar.spider.model;

/**
 * @program: spider-demo
 * @description: FateConst
 * @author: kelin
 * @create: 2020-04-15 15:34
 **/
public class FateConst {
    /**
     * 紫微星宿
     */
    public static final String XINGXIU1 = "XINGXIU1";
    public static final String XINGXIU2 = "XINGXIU2";
    public static final String XINGXIU3 = "XINGXIU3";
    public static final String XINGXIU4 = "XINGXIU4";
    /**
     * 干支
     */
    public static final String GANZHI = "GANZHI";
    /**
     * 紫薇命盘跟path
     */
    public static final String ZWMP_BASEXPATH = "//div[@class=\"mbox01con\"]/table/tbody";

    /**
     * 紫薇命盘个人信息path
     * */
    public static final String ZWMP_CENTERINFO_PATH = "//div[@class=\"mbox01con\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table[2]/tbody/tr[3]/td/table/tbody";

    /**
     * 运势基本信息path
     * */
    public static final String FADE_BASEXPATH = "//div[@class=\"mbox01\"]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/table/tbody";
    /**
     * 身宫
     */
    public static final String SHENGONG = "SHENGONG";
    /**
     * 五行
     */
    public static final String WUXING = "WUXING";
    /**
     * 命主
     */
    public static final String MINGZHU = "MINGZHU";
    /**
     * 斗君
     */
    public static final String DOUJUN = "DOUJUN";
    /**
     * 身主
     */
    public static final String SHENZHU = "SHENZHU";

    /**
     * 性别
     */
    public static final String GENDER = "GENDER";

    /**
     * 属相
     */
    public static final String SHUXIANG = "SHUXIANG";

    /**
     * 运势开始抓取时间
     * */
    public static final String STARTTIME = "1931-1-1 00:00:00";
    /**
     * 运势开始结束时间
     * */
    public static final String ENDTIME = "1931-1-31 23:00:00";

    /**
     * 性别 女
     * */
    public static final String FEMALE = "女";


    /**
     * 求财特征
     * */
    public static final String CAIYUN_QCTZ = "CAIYUN_QCTZ";

    /**
     * 求财特征path
     * */
    public static final String CAIYUN_QCTZ_BASEXPATH = "//div[@class=\"l_form_ok\"]";

    /**
     * 五行旺度
     * */
    public static final String CAIYUN_WUXING_WANGDU = "CAIYUN_WUXING_WANGDU";

    /**
     * 运势post请求参数
     * */
    public static final String PARAMETER_SEX = "StrSex";
    public static final String PARAMETER_YEAR = "StrYear1";
    public static final String PARAMETER_MONTH = "StrMonth";
    public static final String PARAMETER_DAY = "StrDay";
    public static final String PARAMETER_HOUR = "StrTime";


    public static final String STRLNAME = "StrLName";

    public static final String STRFNAME = "StrFName";



    /**
     * 一生財富
     * */
    public static final String YSCF_PINGFEN = "YSCF_PINGFEN";
    public static final String YSCF_XIANGJIE = "YSCF_XIANGJIE";

    /**
     * 正財運
     * */
    public static final String ZCY_PINGFEN = "ZCY_PINGFEN";
    public static final String ZCY_XIANGJIE = "ZCY_XIANGJIE";
    /**
     * 偏財運
     * */
    public static final String PCY_PINGFEN = "PCY_PINGFEN";
    public static final String PCY_XIANGJIE = "PCY_XIANGJIE";
    /**
     * 破財幾率
     * */
    public static final String POCAI_PINGFEN = "POCAI_PINGFEN";
    public static final String POCAI_XIANGJIE = "POCAI_XIANGJIE";

    /**
     * 青年财运
     * */
    public static final String QNCY_PINGFEN = "QNCY_PINGFEN";
    public static final String QNCY_XIANGJIE = "QNCY_XIANGJIE";
    /**
     * 中年财运
     * */
    public static final String ZNCY_PINGFEN = "ZNCY_PINGFEN";
    public static final String ZNCY_XIANGJIE = "ZNCY_XIANGJIE";
    /**
     * 老年财运
     * */
    public static final String LNCY_PINGFEN = "LNCY_PINGFEN";
    public static final String LNCY_XIANGJIE = "LNCY_XIANGJIE";


}
