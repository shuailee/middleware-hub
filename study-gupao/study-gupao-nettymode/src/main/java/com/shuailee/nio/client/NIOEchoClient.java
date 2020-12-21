package com.shuailee.nio.client;

import com.shuailee.common.ServerInfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOEchoClient {

    public static void main(String[] args) throws Exception {
        NIOEchoClient client=new NIOEchoClient();
        client.initClient();
    }



    private Selector selector;          //创建一个选择器
    private final static int BUF_SIZE = 10240;
    private static ByteBuffer bb = ByteBuffer.allocate(BUF_SIZE);


    private void  initClient() throws IOException {
        // 1 创建通道管理器对象selector
        this.selector = Selector.open();

        // 2 创建一个通道对象channel
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);       //将通道设置为非阻塞
        channel.connect(new InetSocketAddress(ServerInfo.PORT));//将通道链接绑定在8686端口

        // 3 向Selector注册Channel事件，然后调用它的select()方法。这个方法会一直阻塞到某个注册的通道有事件就绪。一旦这个方法返回，线程就可以处理这些事件
        // 将上述的通道管理器selector和通道绑定，并为该通道注册OP_ACCEPT事件
        //注册事件后，当该事件到达时，selector.select()会返回（一个key），如果该事件没到达selector.select()会一直阻塞

        SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_CONNECT); //链接事件

        // 4  轮询
        while (true) {
            //这是一个阻塞方法，一直等待直到有数据可读，返回值是key的数量（可以有多个）
            selector.select();
            //如果channel有数据了，将生成的key访入keys集合中
            Set keys = selector.selectedKeys();
            //遍历key
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                //得到集合中的一个key实例
                SelectionKey key = (SelectionKey) iterator.next();
                iterator.remove();          //拿到当前key实例之后记得在迭代器中将这个元素删除
                //判断当前key所代表的channel是否在Acceptable状态，如果是就进行接收
                if (key.isConnectable()) {
                    doConnect(key);
                } else if (key.isReadable()) {
                    doRead(key);
                }
            }
        }
    }



    public void doConnect(SelectionKey key) throws IOException {
        //通过key找到注册的channel'
        SocketChannel clientChannel = (SocketChannel) key.channel();
        if (clientChannel.isConnectionPending()){
            clientChannel.finishConnect();
        }
        //将通道设置为非阻塞
        clientChannel.configureBlocking(false);
        //往通道写入内容发送到服务端
        write(clientChannel,key);

    }


    public void doRead(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        bb.clear();
        clientChannel.read(bb);
        byte[] data = bb.array();
        String msg = new String(data).trim();
        System.out.println("服务端返回消息："+msg);
        //注册一个新的OP_READ绑定
        write(clientChannel,key);
    }

/**
 * 往通道写入内容发送到服务端
 * */
    public void write(SocketChannel clientChannel,SelectionKey key) throws IOException {
        // 1  发送内容到服务器
        bb.clear();
        String msg = "服务端你好!!"+ServerInfo.getString("请输入要发送的内容：") ;
        bb.put(msg.getBytes("UTF-8")) ; // 将此数据保存在缓冲区之中
        bb.flip() ; // 重置缓冲区
        clientChannel.write(bb) ; // 发送数据内容

        //注册一个读取事件，等待读取服务端应答
        clientChannel.register(key.selector(),SelectionKey.OP_READ);
    }
}
