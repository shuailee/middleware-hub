package com.shuailee.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class WatcherDemo {
    public static void main(String[] args) {
        try {
            final CountDownLatch latch = new CountDownLatch(1);
            //zk建立链接短暂的过程中存在两个状态CONNECTING，CONNECTED
           final ZooKeeper  zooKeeper = new ZooKeeper("127.0.0.1:2181", 40000,
                    new Watcher() {
                        @Override
                        public void process(WatchedEvent watchedEvent) {
                            System.out.println("默认事件类型："+watchedEvent.getType());
                            if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                                //如果watch收到了服务端的响应事件，则链接成功
                                latch.countDown();
                            }
                        }
                    });
            latch.await();
            System.out.println(zooKeeper.getState());

            //创建节点
            zooKeeper.create("/shuailee-zk","1".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            //针对节点绑定事件
            //通过exists绑定
            Stat stat= zooKeeper.exists("/shuailee-zk", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println(watchedEvent.getType()+"->"+ watchedEvent.getPath());
                    try {
                        //当该事件触发过一次以后会被删除，所以这里在每次触发后再绑定一次事件
                        zooKeeper.exists(watchedEvent.getPath(), true);//参数设置为true默认使用的是全局的默认事件
                    }catch (Exception ex){

                    }
                }
            });
            //触发事件（通过修改的操作来触发）
            stat = zooKeeper.setData("/shuailee-zk","2".getBytes(),stat.getVersion());
            //删除节点（因为前面的修改意见触发了一次事件，所以删除操作理论上不会再触发了。需要循环绑定事件才能继续触发）
            zooKeeper.delete("/shuailee-zk",stat.getVersion());
            System.in.read();

            zooKeeper.close();

        } catch (Exception ex) {
            System.out.println("connection exception");
        }
    }
}
