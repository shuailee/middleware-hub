package com.shuailee.study.tuomin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @package: com.shuailee.study.tuomin
 * @description:
 * @author: klein
 * @date: 2021-05-12 18:47
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long useId;

    // 用户编号
    private String useNo;

    // 用户姓名
    private String useName;

    @SensitiveInfo(SensitiveType.MOBILE_PHONE)
    // 用户手机号
    private String mobile;

    // 用户性别
    private String sex;

    // 用户年龄
    private int age;

    // 用户籍贯
    private String nativePlace;

    @SensitiveInfo(SensitiveType.ID_CARD)
    // 用户身份证号
    private String idCard;



}
