package com.shuailee.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class ConnectionDemo {
    public static void main(String[] args) {
        ZooKeeper zooKeeper=null;
        try {
            final CountDownLatch  latch=new CountDownLatch(1);
            //zk建立链接短暂的过程中存在两个状态CONNECTING，CONNECTED
             zooKeeper=new ZooKeeper("127.0.0.1:2181", 40000,
                     new Watcher() {
                         @Override
                         public void process(WatchedEvent watchedEvent) {
                            if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
                                //如果watch收到了服务端的响应事件，则链接成功
                                latch.countDown();
                            }
                         }
                     });
            latch.await();
            System.out.println(zooKeeper.getState());

            //1 创建节点 （节点名称，值：0，访问权限（公开都能访问），节点类型:持久化节点）
            zooKeeper.create("/zk-shuai","0".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
            Thread.sleep(1000);
            //stat代表客户端的get命令获取到的数据
            Stat stat=new Stat();
            //得到节点的值并输出
            byte [] bytes= zooKeeper.getData("/zk-shuai",null,stat);
            System.out.println(new String(bytes));

            //2 修改指定节点指定版本号的节点的值
            zooKeeper.setData("/zk-shuai","1".getBytes(),stat.getVersion());
            //得到节点的值并输出
            byte [] bytes2= zooKeeper.getData("/zk-shuai",null,stat);
            System.out.println(new String(bytes2));

            //4 删除节点 - 此时可以去控制台验证该节点是否存在: ls /zk-shuai
            zooKeeper.delete("/zk-shuai",stat.getVersion());

        }catch (Exception ex){
            System.out.println("connection exception");
        }
        finally {
            if(zooKeeper!=null) {
                try {
                    zooKeeper.close();
                } catch (InterruptedException e) {
                    System.out.println("close exception");
                }
            }
        }
    }
}
