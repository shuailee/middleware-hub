package com.shuailee.rmidemo.server.simplerpc;

public class SimpleSpeakMain {
    public static void main(String[] args) {
        ISimpleSpeak hello=new SimpleSpeakImpl();
        SimpleSpeakServer server=new SimpleSpeakServer();
        server.publisher(hello,8888);

    }
}
