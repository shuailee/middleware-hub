package com.shuailee.study.basicknowledge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.platform.commons.util.StringUtils;

/**
 * @program: study-javacore
 * @description: 字符串工具类
 * @author: shuai.li
 * @create: 2018-09-11 14:40
 **/
public class StringUtil {


    /**
     * 简体中文的正则表达式。
     */
    private static final String REGEX_SIMPLE_CHINESE = "^[\u4E00-\u9FA5]+$";

    /**
     * 字母数字的正则表达式。
     */
    private static final String REGEX_ALPHANUMERIC = "[a-zA-Z0-9]+";

    /**
     * 整数或浮点数的正则表达式。
     */
    private static final String REGEX_NUMERIC = "(\\+|-){0,1}(\\d+)([.]?)(\\d*)";

    /**
     * 身份证号码的正则表达式。
     */
    private static final String REGEX_ID_CARD = "(\\d{14}|\\d{17})(\\d|x|X)";

    /**
     * 电子邮箱的正则表达式。
     */
    private static final String REGEX_EMAIL = ".+@.+\\..+";


    /**
     * 判断字符串是否匹配了正则表达式。
     *
     * @param str   字符串
     * @param regex 正则表达式
     * @return true/false
     */
    public static boolean isRegexMatch(String str, String regex) {
        return str != null && str.matches(regex);
    }

    /**
     * 是否是简体中文字符串。
     *
     * @param str 字符串
     * @return true/false
     */
    public static boolean isSimpleChinese(String str) {
        return isRegexMatch(str, REGEX_SIMPLE_CHINESE);
    }

    /**
     * 是否包含简体中文字符串。
     *
     * @param str 字符串
     * @return true/false
     */
    public static boolean isContainsSimpleChinese(String str){
        for(char c: str.toCharArray()) {
            if (isSimpleChinese(new String(new char[]{c}))) {
                return true;
            }
        }
        return false;
    }

    /**
     * desc: 数字验证
     * @parameter: [validatedString]
     * @return: boolean
     */
    public static boolean numbericValidate(String validatedString)
    {
        if(StringUtils.isNotBlank(validatedString)) {
            //final String REGEX_NUMBER = "^[-]?\\d+[.]?\\d*$";
            Pattern pattern = Pattern.compile(REGEX_NUMERIC);
            Matcher matcher = pattern.matcher(validatedString);
            return matcher.find();
        }
        return false;
    }
}
