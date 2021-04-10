package cn.ply.cloud.java.jvm.classLoading;

/**
 * @Author ply
 * @Description 类加载测试
 * @Date created in 2021/04/08
 * @ModifiedBy
 */
public class Main {
    /**
     * 类的生命周期： 加载 - 验证 - 准备-解析 - 初始化 - 使用 - 卸载
     */

    public static void main(String[] args) {
        /**
         * 被动引用类时不会触发类的初始化，被动引用分以下三种情况
         */
        /**
         * 1、通过子类引用父类静态字段，不会导致子类初始化
         */
        System.out.println(SubClass.value);
        /**
         * 2、类的常量字段在编译阶段会存入调用类的常量池中，不会直接引用到定义常量的类，不会导致类初始化
         */
        System.out.println(SubClass.finalS);
        /**
         * 3、通过定义数组引用类，不会触发类的初始化
         */
        SubClass[] subClasses = new SubClass[10];

        /**
         * 当一个接口初始化时，并不要求其父接口全部完成初始化，只有在使用到父接口时才会初始化
         */

    }
}
