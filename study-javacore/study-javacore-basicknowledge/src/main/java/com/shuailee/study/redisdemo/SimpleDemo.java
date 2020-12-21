package com.shuailee.study.redisdemo;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

import java.util.HashSet;
import java.util.Set;

public class SimpleDemo {

    //https://www.cnblogs.com/c-xiaohai/p/8376364.html
    private static JedisCluster jedisCluster;
    static {
        // 添加集群的服务节点Set集合
        Set<HostAndPort> hostAndPortsSet = new HashSet<HostAndPort>();
        // 添加节点
        hostAndPortsSet.add(new HostAndPort("127.0.0.1", 31001));
        hostAndPortsSet.add(new HostAndPort("127.0.0.1", 31002));
        hostAndPortsSet.add(new HostAndPort("127.0.0.1", 31003));
        hostAndPortsSet.add(new HostAndPort("127.0.0.1", 32001));
        hostAndPortsSet.add(new HostAndPort("127.0.0.1", 32002));
        hostAndPortsSet.add(new HostAndPort("127.0.0.1", 32003));
        // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(100);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(500);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000);
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);
        jedisCluster = new JedisCluster(hostAndPortsSet, jedisPoolConfig);
    }


    public static void main(String[] args) {
        //集群测试
        System.out.println("判断某个键是否存在："+jedisCluster.exists("username"));
        System.out.println("新增<'username','wukong'>的键值对："+jedisCluster.set("username", "wukong"));
        System.out.println("是否存在:"+jedisCluster.exists("username"));
        System.out.println("username:"+jedisCluster.get("username"));
    }

    /**
     * 单机连接
     * */
    private static void simpledemo1() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println("判断某个键是否存在："+jedis.exists("Jedis"));
        jedis.set("Jedis", "Hello Work!",new SetParams().ex(10));
        System.out.println(jedis.get("Jedis"));
        //System.out.println("清空数据："+jedis.flushDB()); //删除redis中所有key
        System.out.println("判断某个键是否存在："+jedis.exists("Jedis"));
        jedis.close();
    }

}
