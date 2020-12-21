package com.shuailee.observer.guavademo;

import com.google.common.eventbus.EventBus;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-05 22:42
 **/
public class GuavaEventTest {
    public static void main(String[] args) {
        EventBus eventbus = new EventBus();

        GuavaEvent guavaEvent = new GuavaEvent();
        eventbus.register(guavaEvent);
        eventbus.post("Tom");
    }
}
