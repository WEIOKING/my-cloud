package cn.ply.cloud.java.jvm.classLoading;

/**
 * @Author ply
 * @Description 类加载测试子类
 * @Date created in 2021/04/08
 * @ModifiedBy
 */
public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!!!");
    }
    public static final String finalS = "finalStr";
}
