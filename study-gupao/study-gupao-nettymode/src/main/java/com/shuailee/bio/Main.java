package com.shuailee.bio;

public class Main {

    /*
        Socket类
            Socket(InetAddress address, int port)  创建一个流套接字并将其连接到指定 IP 地址的指定端口号。
            close() 关闭此套接字。
            connect(SocketAddress endpoint)  将此套接字连接到服务器
            getInetAddress() 返回套接字连接的地址。
            getInputStream() 客户端往服务端的输入流
            getOutputStream() 返回此套接字的输出流
            getLocalPort() 返回此套接字绑定到的本地端口。
            getPort() 返回此套接字连接到的远程端口。
        ServerSocket类
            ServerSocket(int port)  创建绑定到特定端口的服务器套接字。
            accept() 侦听并接受到此套接字的连接。
            getInetAddress() 返回此服务器套接字的本地地址
        Socket编程步骤：
           1 服务器端创建ServerSocket对象，调用accept方法返回Socket对象
           2 客户端创建Socket对象，通过端口连接到服务器
           3 客户端、服务器端都使用Socket中的getInputStream方法和getOutputStream方法获得输入流和输出流，进一步进行数据读写操作
    * */


    //bio 同步阻塞io
    public static void main(String [] args)
    {
        System.out.println("hello world");
    }
}
