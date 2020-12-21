package com.shuailee.adapter.login.loginadapter;

import com.shuailee.adapter.login.ResultMsg;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-04 21:51
 **/
public class LoginForTelAdapter implements LoginAdapter {
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForTelAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return null;
    }
}
