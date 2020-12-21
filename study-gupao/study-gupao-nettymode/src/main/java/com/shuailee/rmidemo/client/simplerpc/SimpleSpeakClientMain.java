package com.shuailee.rmidemo.client.simplerpc;

public class SimpleSpeakClientMain {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy=new RpcClientProxy();
        ISimpleSpeak speak= rpcClientProxy.clientProxy(ISimpleSpeak.class,"localhost",8888);
        System.out.println(speak.sayHello("hairui"));

    }
}
