package com.shuailee.observer.guavademo;

import com.google.common.eventbus.Subscribe;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-05 22:41
 **/
public class GuavaEvent {
    /**
     * 订阅方法
     * */
    @Subscribe
    public void subscribe(String str){
        //业务逻辑
        System.out.println("执行subscribe订阅方法,传入的参数是:" + str);
    }
}
