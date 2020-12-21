package com.shuailee.rmidemo.server;

import com.shuailee.rmidemo.IHelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 * 一个简单的远程调用
 * */
public class HelloServiceImpl extends UnicastRemoteObject implements  IHelloService {
    //看super源码可知道， new 的过程中已经发布了一个远程对象
    //内部通过反射代理的方式
    protected HelloServiceImpl() throws RemoteException {
         super();
    }

    @Override
    public String sayHello(String msg) throws RemoteException{
        return "hello:"+ msg;
    }
}
