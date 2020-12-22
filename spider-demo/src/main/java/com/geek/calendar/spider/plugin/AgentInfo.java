package com.geek.calendar.spider.plugin;

import com.geek.calendar.spider.model.PoxyBean;
import com.geek.calendar.spider.utils.FileUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.proxy.Proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: spider-demo
 * @description: AgentInfo
 * @author: kelin
 * @create: 2020-04-20 17:26
 **/
@Slf4j
public class AgentInfo {
    static String proxy_username = "cctvendtp1";
    static String proxy_passwd = "21112888";
    public static String proxy_server = "183.129.244.xx";
    static String proxy_port = "88";
    static String pattern = "json";
    static int num = 1;
    static String key_name = "user_name=";
    static String key_timestamp = "timestamp=";
    static String key_md5 = "md5=";
    static String key_pattern = "pattern=";
    static String key_num = "number=";
    static String key_port = "port=";

    public static long get_timestamp() {
        long timestamp = System.currentTimeMillis();
        return timestamp;
    }

    public static String get_md5_str(String str) {
        byte[] buf = str.getBytes();
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception var9) {
            System.out.println(var9.getMessage());
        }

        md5.update(buf);
        byte[] tmp = md5.digest();
        StringBuilder sb = new StringBuilder();
        byte[] var8 = tmp;
        int var7 = tmp.length;

        for (int var6 = 0; var6 < var7; ++var6) {
            byte b = var8[var6];
            if (b > 0 && b < 16) {
                sb.append("0");
            }

            sb.append(Integer.toHexString(b & 255));
        }

        return sb.toString();
    }


    public static String get_open_url() {
        long time_stamp = get_timestamp();
        String md5_str = get_md5_str(proxy_username + proxy_passwd + time_stamp);
        return "http://" + proxy_server + ":" + proxy_port + "/open?" + key_name + proxy_username + "&" + key_timestamp + time_stamp + "&" + key_md5 + md5_str + "&" + key_pattern + pattern + "&" + key_num + num;
    }

    public static String get_close_url(String auth_port) {
        long time_stamp = get_timestamp();
        String md5_str = get_md5_str(proxy_username + proxy_passwd + time_stamp);
        return "http://" + proxy_server + ":" + proxy_port + "/close?" + key_name + proxy_username + "&" + key_timestamp + time_stamp + "&" + key_md5 + md5_str + "&" + key_pattern + pattern + "&" + key_port + auth_port;
    }

    public static String get_reset_url() {
        long time_stamp = get_timestamp();
        String md5_str = get_md5_str(proxy_username + proxy_passwd + time_stamp);
        return "http://" + proxy_server + ":" + proxy_port + "/reset_ip?" + key_name + proxy_username + "&" + key_timestamp + time_stamp + "&" + key_md5 + md5_str + "&" + key_pattern + pattern;
    }


    public static List<Proxy> buildTestProxyIP() throws IOException {
        int[] port = {11126, 26587, 45032, 40082, 19750, 56135, 25075, 8557, 49431, 8284};
        List<Proxy> proxies = new ArrayList<Proxy>();
        if (port != null) {
            for (int i : port) {
                proxies.add(new Proxy(proxy_server, i));
            }
        }
        return proxies;
    }

    /**
     * 如果購買總數是100個ip，每次獲取一個就少一個
     * 每個ip的有效時間為30分鐘
     */
    public synchronized static List<Proxy> buildProxyIP() throws IOException {

        List<Proxy> proxies = new ArrayList<Proxy>();
        String msg;
        String r_close;
        try {
            msg = get_open_url();
            URL getUrl = new URL(msg);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setConnectTimeout(300000);
            connection.setReadTimeout(300000);
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String r = "";
            while ((r_close = reader.readLine()) != null) {
                r = r + r_close;
            }
            reader.close();
            log.info("代理ip:" + r);
            Gson gson = new Gson();
            PoxyBean poxyBean = (PoxyBean) gson.fromJson(r.toString(), PoxyBean.class);

            int[] port = null;
            if (poxyBean.code == 108) {
                String reset_url = get_reset_url();
                URL get_reset_url = new URL(reset_url);
                HttpURLConnection reset_connection = (HttpURLConnection) get_reset_url.openConnection();
                reset_connection.setConnectTimeout(300000);
                reset_connection.setReadTimeout(300000);
                reset_connection.connect();
            } else if (poxyBean.code == 100) {
                port = poxyBean.port;
            }


            if (port != null) {
                // 獲取到以後設置緩存
                // RedisUtil.setIPPool(port);
                for (int i : port) {
                    proxies.add(new Proxy(proxy_server, i));
                }
            }
        } catch (Exception ex) {
            log.info("代理異常:" + ex);
        }
        return proxies;
    }



}
