package cn.ply.cloud.java.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/29
 * @ModifiedBy
 */
public class DynamicProxyHandler implements InvocationHandler {
    private Object prosied;

    public DynamicProxyHandler(Object prosied) {
        this.prosied = prosied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy:" + proxy.getClass().getName() + ", method:" + method.getName() + ", args:" + args);
        return method.invoke(prosied, args);
    }
}
