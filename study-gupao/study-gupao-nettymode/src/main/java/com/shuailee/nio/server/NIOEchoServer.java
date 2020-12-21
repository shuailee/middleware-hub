package com.shuailee.nio.server;

import com.shuailee.common.ServerInfo;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOEchoServer {
    public static void main(String[] args) throws Exception {
        NIOEchoServer server=new NIOEchoServer();
        server.initServer();
    }


    /**
     *  1 Selector:
             选择器是NIO的核心，它是channel的管理者
             通过执行select()阻塞方法，监听是否有channel准备好
             一旦有数据可读，此方法的返回值是SelectionKey的数量

             所以服务端通常会死循环执行select()方法，直到有channl准备就绪，然后开始工作
             每个channel都会和Selector绑定一个事件，然后生成一个SelectionKey的对象:

             SocketChannel clientChannel = SocketChannel.open();
             clientChannel.configureBlocking(false);
             clientChannel.connect(new InetSocketAddress(port));
             clientChannel.register(selector, SelectionKey.OP_CONNECT);

             需要注意的是：
             channel和Selector绑定时，channel必须是非阻塞模式
             而FileChannel不能切换到非阻塞模式，因为它不是套接字通道，所以FileChannel不能和Selector绑定事件

             在NIO中一共有四种事件：
             1.SelectionKey.OP_CONNECT：连接事件
             2.SelectionKey.OP_ACCEPT：接收事件
             3.SelectionKey.OP_READ：读事件
             4.SelectionKey.OP_WRITE：写事件
         2  Channel
             共有四种通道：
             FileChannel：作用于IO文件流
             DatagramChannel：作用于UDP协议
             SocketChannel：作用于TCP协议
             ServerSocketChannel：作用于TCP协议
     * */


    private Selector selector;          //创建一个选择器
    private final static int BUF_SIZE = 10240;
    private void initServer() throws IOException {
        // 1 创建通道管理器对象selector
        this.selector=Selector.open();

        // 2 创建一个通道对象channel
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);       //将通道设置为非阻塞
        channel.socket().bind(new InetSocketAddress(ServerInfo.PORT));       //将通道绑定在8686端口

        // 3 向Selector注册Channel事件，然后调用它的select()方法。这个方法会一直阻塞到某个注册的通道有事件就绪。一旦这个方法返回，线程就可以处理这些事件
        // 将上述的通道管理器selector和通道绑定，并为该通道注册OP_ACCEPT事件
        //注册事件后，当该事件到达时，selector.select()会返回（一个key），如果该事件没到达selector.select()会一直阻塞
        //注册一个接收事件事件，等待接收客户端链接
        SelectionKey selectionKey = channel.register(selector,SelectionKey.OP_ACCEPT);

        // 4  轮询
        while (true){
            //这是一个阻塞方法，一直等待直到有数据可读，返回值是key的数量（可以有多个）
            selector.select();
            ////如果channel有数据了，将生成的key访入keys集合中
            Set keys =selector.selectedKeys();
            //遍历key
            Iterator iterator=keys.iterator();
            while (iterator.hasNext()){
                //得到集合中的一个key实例
                SelectionKey key= (SelectionKey)iterator.next();
                iterator.remove();          //拿到当前key实例之后记得在迭代器中将这个元素删除
                //判断当前key所代表的channel是否在Acceptable状态，如果是就进行接收
                if(key.isAcceptable()){
                    doAccept(key);
                }else if (key.isReadable()){
                    doRead(key);
                }else if (key.isWritable() && key.isValid()){
                    doWrite(key);
                }else if (key.isConnectable()){
                    System.out.println("连接成功！");
                }

            }

        }

    }

    public void doAccept(SelectionKey key) throws IOException {
        //获取channel
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        System.out.println("ServerSocketChannel正在循环监听");
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false);
        //注册一个读取事件，等待读取客户端输入
        clientChannel.register(key.selector(),SelectionKey.OP_READ);
    }
    /*
        如果数据在写入传输过程中有编码此处还需解码
     */
    public void doRead(SelectionKey key) throws IOException {

        SocketChannel clientChannel = (SocketChannel) key.channel();
        // 1 创建字节 buffer缓冲区
        ByteBuffer bb=ByteBuffer.allocate(BUF_SIZE);
        // 2 通道中的数据读取到（写入）缓冲区内
        long bytesRead = clientChannel.read(bb);
        while (bytesRead>0){
            // 3 刷新缓冲区，重置指针，准备读取缓冲区数据
            bb.flip();
            // 4 将缓冲区数据转为字节数组，并转为字符保存
            byte [] data= bb.array();
            String info=new String(data).trim();

            System.out.println("从客户端发送过来的消息是："+info);
            bb.clear();
            bytesRead = clientChannel.read(bb);

        }



        //注册一个服务端写入应答事件，等待服务端写入回应
        clientChannel.register(key.selector(),SelectionKey.OP_WRITE);


    }

    public void doWrite(SelectionKey key) throws IOException {
        // 1 创建字节 buffer缓冲区
        ByteBuffer bb = ByteBuffer.allocate(BUF_SIZE);
        bb.clear();
        // 服务端应答
        String writeMessage = "【ECHO】服务端应答信息是：hello"  ; // 结束消息
        bb.put(writeMessage.getBytes());

        // 3 刷新缓冲区，重置指针，准备写入缓冲区数据
        bb.flip();
        SocketChannel clientChannel = (SocketChannel) key.channel();
        clientChannel.write(bb);

        //注册一个读取事件，等待读取客户端输入
        clientChannel.register(key.selector(),SelectionKey.OP_READ);

    }


}
