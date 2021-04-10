package cn.ply.cloud.java.jvm.classLoading;

/**
 * @Author ply
 * @Description 类加载测试父类
 * @Date created in 2021/04/08
 * @ModifiedBy
 */
public class SuperClass {
    static {
        System.out.println("SuperClass init!!!");
    }
    public static int value = 123;
}
