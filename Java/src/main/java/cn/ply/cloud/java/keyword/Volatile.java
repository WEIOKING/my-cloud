package cn.ply.cloud.java.keyword;

/**
 * @Author ply
 * @Description volatile 关键字
 * @Date created in 2021/4/8
 * @ModifiedBy
 */
public class Volatile {
    /**
     * volatile 是轻量级的 synchronized，
     * 1、它在多线程开发中保证共享变量的“可见性”,
     *      CPU三级缓存解决CPU运算效率和IO效率的问题，多线程情况下缓存一致性问题带来了数据可见性问题；volatile关键字修饰的变量，
     *      jvm自动在汇编中增加#LOCK指令，该指令会在CPU执行中添加总线锁或缓存锁 ；
     *      总线锁：锁定cpu的前端总线，使同一时刻只有一个线程和内存通信，避免了多线程导致的内存可见性问题；
     *      缓存锁：对总线锁的优化，总线锁导致CPU使用效率下降；缓存锁只针对CPU三级缓存中的目标数据加锁，缓存锁使用MESI缓存一致性协议实现；
     * 2、为了实现volatile 的内存语义，jvm通过内存屏障限制指令重排序。
     *      编译器在不改变程序语义的情况下，去优化整体的性能，导致指令重排序
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
