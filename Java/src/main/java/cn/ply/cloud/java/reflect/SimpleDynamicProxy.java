package cn.ply.cloud.java.reflect;

import java.lang.reflect.Proxy;

/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/29
 * @ModifiedBy
 */
public class SimpleDynamicProxy {
    public static void consumer(DoSomething doSomething){
        doSomething.doSomething();
        doSomething.doOtherthing("*****");
    }
    public static void consumer1(EatSomething eatSomething){
        eatSomething.eatSomething();
    }

    public static void main(String[] args) {
        ReadObject readObject = new ReadObject();
        consumer(readObject);

        //创建动态代理
        DoSomething doSomething = (DoSomething) Proxy.newProxyInstance(DoSomething.class.getClassLoader(), new Class[]{DoSomething.class}, new DynamicProxyHandler(readObject));
        consumer(doSomething);
        EatSomething eatSomething = (EatSomething) Proxy.newProxyInstance(EatSomething.class.getClassLoader(), new Class[]{EatSomething.class}, new DynamicProxyHandler(readObject));
        consumer1(eatSomething);
    }
}
