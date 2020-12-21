package com.shuailee.rmidemo.server.simplerpc;

public class SimpleSpeakImpl implements ISimpleSpeak {
    @Override
    public String sayHello(String msg) {
        return "Hello , "+msg;
    }
}
