package com.shuailee.rmidemo;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 一个简单的远程调用
 * */
public interface IHelloService extends Remote {

     String sayHello(String msg) throws RemoteException;

}
