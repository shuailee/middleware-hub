package com.shuailee.rmidemo.client;

import com.shuailee.rmidemo.IHelloService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 一个简单的远程调用
 * */
public class HelloClientMain {
    public static void main(String[] args) {

        try {
            //模拟服务远程调用（两个项目中是无法new的）
            IHelloService helloService= (IHelloService)Naming.lookup("rmi://127.0.0.1/Hello");
            // 获得HelloServiceImpl实例(HelloServiceImpl_stub代理对象)
            // RegistryImpl_stub（服务单代理对象）
            System.out.println(helloService.sayHello("shuai"));

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
