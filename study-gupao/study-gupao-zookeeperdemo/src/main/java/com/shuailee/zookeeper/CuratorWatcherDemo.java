package com.shuailee.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorWatcherDemo {
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
            //创建监听绑定事件（监听父子节点）
            addListenerWithTreeCache(curatorFramework,"/shuai");

            //创建节点，触发事件,父子都可以（可以直接在控制台创建）（增删改都可触发）
            curatorFramework.create().forPath("/shuai","1".getBytes());

            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 综合父子节点创建的监听事件
     * PathChildCache 监听一个节点下子节点的创建、删除、更新
     * NodeCache  监听一个节点的更新和创建事件
     * TreeCache  综合PatchChildCache和NodeCache的特性
     */
    public static void addListenerWithTreeCache(CuratorFramework curatorFramework,String path) throws Exception {
        TreeCache treeCache=new TreeCache(curatorFramework,path);
        TreeCacheListener treeCacheListener=new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println(event.getType()+"->"+event.getData().getPath());
            }
        };
        treeCache.getListenable().addListener(treeCacheListener);
        treeCache.start();
    }
    /**
     * 子节点的监听事件
     * */
    public static void addListenerWithPathChildCache(CuratorFramework curatorFramework,String path) throws Exception {
        PathChildrenCache pathChildrenCache=new PathChildrenCache(curatorFramework,path,true);
        PathChildrenCacheListener pathChildrenCacheListener=new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("Receive Event:"+event.getType());
            }
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start(PathChildrenCache.StartMode.NORMAL);
    }

    /**
     * 父节点的监听事件
     * */
    public static void addListenerWithNodeCache(CuratorFramework curatorFramework,String path) throws Exception {
        final NodeCache nodeCache=new NodeCache(curatorFramework,path,false);
        NodeCacheListener nodeCacheListener=new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("Receive Event:"+nodeCache.getCurrentData().getPath());
            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }
}
