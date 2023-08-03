package main.java.com.hua.myRPCVersion1.client;


import main.java.com.hua.myRPCVersion1.common.RPCRequest;
import main.java.com.hua.myRPCVersion1.common.RPCResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class ClientProxy implements InvocationHandler {
    // 传入参数Service接口的class对象，反射封装成一个request
    private String host;
    private int port;

    public ClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    // jdk 动态代理， 每一次代理对象调用方法，会经过此方法增强（反射获取request对象，socket发送至客户端）
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // request的构建，使用了lombok中的builder，代码简洁
        RPCRequest request = new RPCRequest(method.getDeclaringClass().getName(),method.getName(),args,method.getParameterTypes());
        //数据传输
        RPCResponse response = IOClient.sendRequest(host, port, request);
        //System.out.println(response);
        return response.getData();
    }
    <T>T getProxy(Class<T> clazz){
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T)o;
    }
}
