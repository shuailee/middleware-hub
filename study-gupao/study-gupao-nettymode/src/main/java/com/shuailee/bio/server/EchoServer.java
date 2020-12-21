package com.shuailee.bio.server;

import com.shuailee.common.ServerInfo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class EchoServerHandle implements AutoCloseable {

    private final ServerSocket serverSocket;

    public EchoServerHandle() throws Exception {
        //ServerSocket创建绑定到特定端口的服务器套接字。
        this.serverSocket = new ServerSocket(ServerInfo.PORT);
        System.out.println("ECHO服务器端已经启动了，该服务在" + ServerInfo.PORT + "端口上监听....");
        this.clientConnect();
    }

    private void clientConnect() throws Exception {
        boolean serverFlag = true;
        while (serverFlag) {
            //serverSocket.accept()侦听并接受到此套接字的连接。
            //Socket 创建一个流套接字并将其连接到指定 IP 地址的指定端口号/指定远程地址上的指定远程端口。
            //每次连接成功一个客户端，则启动一个线程为其服务
            Socket client = serverSocket.accept();
            Thread clientThread = new Thread(() -> {
                try {
                    // Scanner可以包装键盘输入，方便地将键盘输入的内容转换成我们想要的数据类型。
                    //PrintStream可以封装（包装）直接与文件交互的节点流对象OutputStream, 使得编程人员可以忽略设备底层的差异，
                    // 进行一致的IO操作。因此这种流也称为处理流或者包装流。

                    Scanner scan=new Scanner(client.getInputStream()); // 服务器端输入为客户端输出，获取客户端向服务端输入的内容流
                    PrintStream out = new PrintStream(client.getOutputStream()) ;//服务器端的输出为客户端输入，向客户端输出内容的流

                    boolean clientFlag = true ;
                    while(clientFlag) {
                        if (scan.hasNext()) {    // 流中有内容
                            String inputData = scan.next(); // 获得客户端的输入数据
                            if ("exit".equalsIgnoreCase(inputData)) {   // 信息结束
                                clientFlag = false ; // 结束内部的循环
                                //向客户端输出内容的流中写入应答内容
                                out.println("【ECHO】Bye Bye ... kiss"); // 一定需要提供有一个换行机制，否则Scanner不好读取
                            } else {
                                out.println("【ECHO】" + inputData); // 回应信息
                            }
                        }
                    }

                    scan.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }finally {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            clientThread.start();
        }
    }

    @Override
    public void close() throws Exception {
        this.serverSocket.close();
    }
}


/**
 * 实现服务器端的编写开发，采用BIO（阻塞模式）实现开发的基础结构
 */
public class EchoServer {
    public static void main(String[] args) throws Exception {
        new EchoServerHandle() ;
    }
}