package com.shuailee.adapter.login;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-04 21:48
 **/
public class ResultMsg {
    private int code;
    private String msg;
    private Object data;
    public ResultMsg(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
