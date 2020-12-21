package com.shuailee.adapter.login;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-04 22:07
 **/
public class PassportTest {
    public static void main(String[] args) {
        IPassportForThird passportForThird = new PassportForThirdAdapter();
        passportForThird.loginForQQ("");
    }
}
