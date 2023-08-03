package main.java.com.hua.myRPCVersion1.common;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * 在上个例子中，我们的Request仅仅只发送了一个id参数过去，这显然是不合理的，
 * 因为服务端不会只有一个服务一个方法，因此只传递参数服务端不会知道调用那个方法
 * 因此一个RPC请求中，client发送应该是需要调用的Service接口名，方法名，参数，参数类型
 * 这样服务端就能根据这些信息根据反射调用相应的方法
 * 使用java自带的序列化方式（实现接口）
 */

public class RPCRequest implements Serializable {
    // 服务类名，客户端只知道接口名，在服务端中用接口名指向实现类
    private String interfaceName;
    // 方法名
    private String methodName;
    // 参数列表
    private Object[] params;
    // 参数类型
    private Class<?>[] paramsTypes;

    public RPCRequest(String interfaceName, String methodName, Object[] params, Class<?>[] paramsTypes) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.params = params;
        this.paramsTypes = paramsTypes;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Class<?>[] getParamsTypes() {
        return paramsTypes;
    }

    public void setParamsTypes(Class<?>[] paramsTypes) {
        this.paramsTypes = paramsTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RPCRequest request = (RPCRequest) o;
        return Objects.equals(interfaceName, request.interfaceName) && Objects.equals(methodName, request.methodName) && Arrays.equals(params, request.params) && Arrays.equals(paramsTypes, request.paramsTypes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(interfaceName, methodName);
        result = 31 * result + Arrays.hashCode(params);
        result = 31 * result + Arrays.hashCode(paramsTypes);
        return result;
    }

    @Override
    public String toString() {
        return "RPCRequest{" +
                "interfaceName='" + interfaceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params=" + Arrays.toString(params) +
                ", paramsTypes=" + Arrays.toString(paramsTypes) +
                '}';
    }
}
