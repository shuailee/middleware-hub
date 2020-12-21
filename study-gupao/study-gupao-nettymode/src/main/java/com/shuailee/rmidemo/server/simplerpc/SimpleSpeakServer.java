package com.shuailee.rmidemo.server.simplerpc;

import com.shuailee.rmidemo.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleSpeakServer {

    private final ExecutorService executorService=Executors.newCachedThreadPool();

    /**
     * 发布服务
     * */
    public void publisher(final Object service,int port){
        ServerSocket serverSocket=null;
        try {
            //启动一个服务监听
            serverSocket = new ServerSocket(port);
            System.out.println("服务启动成功，端口8888，监听中...");
            //循环监听
            while (true) {
                Socket socket=serverSocket.accept();
                //启动一个线程 处理请求
                executorService.execute(new ProcessorHandler(socket,service));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
/**
 * 处理socket请求
 * */
class ProcessorHandler implements Runnable {
    private Socket socket;
    private Object service; //服务端发布的服务

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }
    /**
     * //处理请求
     * */
    @Override
    public void run() {
        ObjectInputStream inputStream=null;
        ObjectOutputStream outputStream=null;
        try {
            //获取输入流
            inputStream=new ObjectInputStream(socket.getInputStream());
            //反序列化输入流为远程传输的对象
            RpcRequest request=(RpcRequest)inputStream.readObject();
            //通过反射调用本地方法
            Object result=invoke(request); //通过反射去调用本地的方法

            //通过输出流将结果输出给客户端
            outputStream=new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * 通过反射调用服务
     * */
    private Object invoke(RpcRequest request) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //下面均为反射操作，目的是通过反射调用服务
        Object[] args=request.getParameters();
        Class<?>[] types=new Class[args.length];
        for(int i=0;i<args.length;i++){
            types[i]=args[i].getClass();
        }
        Method method=service.getClass().getMethod(request.getMethodName(),types);
        return method.invoke(service,args);
    }
}
