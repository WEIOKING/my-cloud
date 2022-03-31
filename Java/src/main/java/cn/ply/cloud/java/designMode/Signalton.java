package cn.ply.cloud.java.designMode;

/**
 * @Author ply
 * @Description 单例模式
 * @Date created in 2022/3/27
 * @ModifiedBy
 */
public class Signalton {
    private static volatile Signalton instance;

    private Signalton() {
    }

    /**
     * 双重检查机制实现懒加载单例模式
     * @return
     */
    public static Signalton getInstance() {
        if (instance == null) {
            synchronized (Signalton.class) {
                if (instance == null) {
                    instance = new Signalton();
                }
            }
        }
        return instance;
    }

    static class Signal{
        private static final Signalton instance1 = new Signalton();
    }

    /**
     * 匿名内部类方式实现懒加载
     */
    public static Signalton getInstance1(){
        return Signal.instance1;
    }

    /**
     * 饿汉模式单例实现
     */
    private static Signalton instance2 = new Signalton();

    public static Signalton getInstance2() {
        return instance2;
    }
}
