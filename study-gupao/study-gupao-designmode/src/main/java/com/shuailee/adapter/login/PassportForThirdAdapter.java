package com.shuailee.adapter.login;

import com.shuailee.adapter.login.loginadapter.LoginAdapter;
import com.shuailee.adapter.login.loginadapter.LoginForQQAdapter;
import com.shuailee.adapter.login.loginadapter.LoginForSinaAdapter;
import com.shuailee.adapter.login.loginadapter.LoginForTelAdapter;

/**
 * @program: study-gupao
 * @description: 第三方登录自由适配
 * @author: shuai.li
 * @create: 2019-06-04 21:56
 **/
public class PassportForThirdAdapter extends SiginService implements IPassportForThird{
    @Override
    public ResultMsg loginForQQ(String id) {
        return processLogin(id,LoginForQQAdapter.class);
    }

    @Override
    public ResultMsg loginForTelphone(String telphone, String code) {
        return processLogin(telphone,LoginForTelAdapter.class);
    }

    @Override
    public ResultMsg loginForSina(String id, String code) {
        return processLogin(id,LoginForSinaAdapter.class);
    }

    @Override
    public ResultMsg loginForRegist(String username, String passport) {
        super.regist(username,null);
        return super.login(username,null);
    }

    /**
     * 这里用到了简单工厂模式及策略模式
     * */
    private ResultMsg processLogin(String key,Class<? extends LoginAdapter> clazz) {
        try {
            LoginAdapter adapter = clazz.newInstance();
            if (adapter.support(adapter)) {
                return adapter.login(key, adapter);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
