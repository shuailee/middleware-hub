package com.shuailee.adapter.login.loginadapter;

import com.shuailee.adapter.login.ResultMsg;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-04 21:50
 **/
public class LoginForSinaAdapter implements LoginAdapter{
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForSinaAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return null;
    }
}
