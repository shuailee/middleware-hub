package com.shuailee.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorDemo {

    public static void main(String[] args) {

        //Curator是高度封装的zk api
        CuratorFramework curatorFramework=CuratorFrameworkFactory.
                builder().connectString("127.0.0.1:2181").
                sessionTimeoutMs(4000)
                .retryPolicy( //重试机制 ，美1000重试一次，总共重试3次
                        new ExponentialBackoffRetry(1000,3))
                .namespace("curator") //逻辑命名空间，方便管理
                .build();
        //启动链接
        curatorFramework.start();

        try {

            //创建一个父子节点,结果: /curator/shuailee/chidnode
            //原生api中，必须是逐层创建，也就是父节点必须存在，子节点才能创建.此处可以直接创建
            curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath("/shuailee/chidnode","1".getBytes());

            //查询
            Stat stat=new Stat();
            byte [] bytes=curatorFramework.getData()
                    .storingStatIn(stat) //将查询到的状态信息存储到stat
                    .forPath("/shuailee/chidnode");
            System.out.println(new  java.lang.String(bytes));
            //修改
            curatorFramework.setData()
                    .withVersion(stat.getAversion())
                    .forPath("/shuailee/chidnode","2".getBytes());
            //查询
            byte [] bytes1=curatorFramework.getData()
                    .storingStatIn(stat) //将查询到的状态信息存储到stat
                    .forPath("/shuailee/chidnode");
            System.out.println(new  java.lang.String(bytes1));

            //删除子节点
            curatorFramework.delete()
                    .deletingChildrenIfNeeded()
                    .forPath("/shuailee/chidnode");

        } catch (Exception e) {
            e.printStackTrace();
        }
        curatorFramework.close();

    }

}

