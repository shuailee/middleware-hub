package com.shuailee.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerInfo {
    public static final int PORT = 6789 ;   // 定义服务器端的访问端口
    public static final String ECHO_SERVER_HOST = "localhost" ; // 要访问的远程主机名称



    // 对于键盘输入数据的操作而言，很明显使用BufferedReader要比使用Scanner更加方便
    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in)) ;

    /**
     * 获取键盘输入
     * */
    public static String getString(String prompt) {
        String returnData = null ;  // 进行接收数据的返回
        boolean flag = true ; // 进行数据验证的基础逻辑判断
        while(flag) {
            System.out.print(prompt);
            try {
                returnData = KEYBOARD_INPUT.readLine();    // 通过键盘读取数据
                if (returnData == null || "".equals(returnData)) {
                    System.err.println("输入的数据不允许为空！");
                } else {
                    flag = false ; // 结束循环
                }

            }catch (Exception e){
                System.err.println("输入的数据错误！");
            }
        }
        return returnData;
    }
}
