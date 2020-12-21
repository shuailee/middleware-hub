package com.shuailee.bio.client;

import com.shuailee.common.ServerInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    public static void main(String [] args){
        //创建一个客户端连接到服务端
        try (EchoClientHandler echo = new EchoClientHandler()) {

        } catch(Exception e) {}
    }
}

class EchoClientHandler implements AutoCloseable {
    private Socket client;

    /**
    * 创建socket客户端并发起服务器连接
    * */
    public EchoClientHandler() throws IOException {
        this.client = new Socket(ServerInfo.ECHO_SERVER_HOST, ServerInfo.PORT);
        System.out.println("已经成功的连接到了服务器端，可以进行消息的发送处理。");
        this.accessServer();
    }

    private void accessServer() throws IOException {
        // Scanner可以包装键盘输入，方便地将键盘输入的内容转换成我们想要的数据类型。
        // PrintStream可以封装（包装）直接与文件交互的节点流对象OutputStream, 使得编程人员可以忽略设备底层的差异，
        // 进行一致的IO操作。因此这种流也称为处理流或者包装流。

        Scanner scan = new Scanner(this.client.getInputStream()) ;  // 监听服务端套接字输入到本客户端的内容，服务器端的输出为客户端的输入
        PrintStream out = new PrintStream(this.client.getOutputStream()) ; // 向服务器端发送内容

        boolean flag = true ;
        while (flag){
            //获取键盘输入
            String data = ServerInfo.getString("请输入要发送的数据信息：") ;
            out.println(data);//将键盘输入内容写入到 服务端发送流，向服务器端发送内容

            if ("exit".equalsIgnoreCase(data)) {
                flag = false ; // 结束循环s
            }
            //获取服务端套接字输入的内容
            if (scan.hasNext()) {
                System.out.println(scan.next());
            }
        }
    }


    // 对于键盘输入数据的操作而言，很明显使用BufferedReader要比使用Scanner更加方便
    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in)) ;



    @Override
    public void close() throws Exception {
        client.close();
    }
}