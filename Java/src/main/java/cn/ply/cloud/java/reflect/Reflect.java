package cn.ply.cloud.java.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/29
 * @ModifiedBy
 */
public class Reflect {
    private String string;
    private long aLong;

    public Reflect() {
    }

    public Reflect(String string) {
        this.string = string;
    }

    public Reflect(String string, long aLong) {
        this.string = string;
        this.aLong = aLong;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public long getaLong() {
        return aLong;
    }

    public void setaLong(long aLong) {
        this.aLong = aLong;
    }

    public static void  main(String[] args) {
        Reflect reflect = new Reflect();


    }

    public void test(Object o) {
        Class<?> aClass = o.getClass();
        try {
            Constructor<?> constructor = aClass.getConstructor();
            Object reflect1 = constructor.newInstance();
            aClass.getFields();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
