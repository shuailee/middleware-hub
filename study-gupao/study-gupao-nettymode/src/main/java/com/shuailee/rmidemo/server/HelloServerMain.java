package com.shuailee.rmidemo.server;

import com.shuailee.rmidemo.IHelloService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class HelloServerMain {
    /**
     * 一个简单的远程调用
     * 发布服务
     * */
    public static void main(String[] args) {
        try {
            //new HelloServiceImpl  实际上已经发布了一个远程对象
            //通过反射代理的方式
            IHelloService helloService=new HelloServiceImpl();
            //发布监听
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://127.0.0.1/Hello",helloService);
            System.out.println("服务启动成功");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
