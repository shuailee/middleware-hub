package com.shuailee.adapter.login;

/**
 * @program: study-gupao
 * @description: 第三方登录兼容接口IPassportForThird
 * @author: shuai.li
 * @create: 2019-06-04 21:53
 **/
public interface IPassportForThird {

    ResultMsg loginForQQ(String id);

    ResultMsg loginForTelphone(String telphone, String code);

    ResultMsg loginForSina(String telphone, String code);

    ResultMsg loginForRegist(String username, String passport);
}
