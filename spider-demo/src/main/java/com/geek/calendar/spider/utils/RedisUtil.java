package com.geek.calendar.spider.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.*;
import redis.clients.jedis.params.SetParams;

import java.util.*;

@Slf4j
public class RedisUtil {


    private final static String PORTNAME_KEY="spider.poxyport";




    public static String setIPPool(int [] port){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        try {

            String vaule = jedis.set(PORTNAME_KEY, Arrays.toString(port), new SetParams().ex(60 * 29));
            return vaule;
        }catch (Exception ex){
            log.info(ex.toString());
        }
        finally {
            jedis.close();
        }
        return null;
    }
    public static String [] getIPPool(){
        Jedis  jedis = new Jedis("127.0.0.1",6379);
        try {
            if (jedis.exists(PORTNAME_KEY)) {
                String vaule = jedis.get(PORTNAME_KEY);
                return vaule.replace("[", "").replace("]", "").split(",");
            }
        }catch (Exception ex){
            log.info(ex.toString());
        }finally {
            jedis.close();
        }
        return null;
    }





}
