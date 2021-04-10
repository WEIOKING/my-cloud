package cn.ply.cloud.java.keyword;

/**
 * @Author ply
 * @Description volatile 关键字
 * @Date created in 2021/4/8
 * @ModifiedBy
 */
public class Volatile {
    /**
     * volatile 是轻量级的 synchronized，它在多线程开发中保证变量的“可见性”,为了实现volatile 的内存语义，jvm回限制指令重排序。
     */
    /**
     * 使用双重检测机制实现单例模式时，实例必须加 volatile 关键字
     * 新建对象可分解为三个动作，分配内存空间、初始化对象、引用指向分配的地址，第二和第三步可能会被重排序，导致多线程时获取到的对象未被初始化。
     * 加上 volatile 关键字后，可以防止指令重排序
     */
    private static volatile Volatile aVolatile;

    public static Volatile getInstance(){
        if (aVolatile == null){
            synchronized (Volatile.class){
                if (aVolatile == null){
                    aVolatile = new Volatile();
                }
            }
        }
        return aVolatile;
    }

    public static void main(String[] args) {
        Volatile.getInstance();
    }
}
