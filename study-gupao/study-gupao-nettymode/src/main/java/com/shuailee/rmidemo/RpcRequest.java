package com.shuailee.rmidemo;

import java.io.Serializable;

public class RpcRequest implements Serializable {
    private static final long serialVersionUID = -9100893052391757993L;
    private String className;
    private String methodName;
    private Object[] parameters;
    /**
     * 类名
     * */
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    /**
     * 方法名
     * */
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    /**
     * 参数
     * */
    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
