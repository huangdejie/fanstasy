package com.cashbang.netty.rpc.protocol;

import java.io.Serializable;

/**
 * @Author: huangdejie
 * @Date: 2019/6/25
 */
public class InvokerProtocol implements Serializable {

    private static final long serialVersionUID = 114389408906684056L;

    //服务名
    private String className;

    //方法名，具体的逻辑
    private String methodName;

    //形参列表
    private Class<?>[] parames;

    //实参列表
    private Object[] values;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParames() {
        return parames;
    }

    public void setParames(Class<?>[] parames) {
        this.parames = parames;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
