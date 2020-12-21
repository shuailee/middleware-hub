package com.shuailee.adapter.login.loginadapter;

import com.shuailee.adapter.login.ResultMsg;

/**
 * @program: study-gupao
 * @description: 根据不
同的登录方式，创建不同的Adapter
 * @author: shuai.li
 * @create: 2019-06-04 21:47
 **/
public interface LoginAdapter {
    boolean support(Object adapter);
    ResultMsg login(String id, Object adapter);
}
