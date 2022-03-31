package cn.ply.cloud.java.keyword;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author ply
 * @Description final关键字通常指“这是无法改变的”
 * @Date created in 2019/6/3
 * @ModifiedBy
 */
public class Final {
    /**
     * final修饰数据：
     *      对于基本数据类型，final使数值恒定不变；而对于对象引用，final使引用恒定不变。但是对象自身却可以被修改。
     *      Java允许生成未初始化的final修饰数据，但编译器都确保final修饰数据在使用前必须被初始化。
     */
    class Poppet {
        private int i;
        Poppet(int i) {
            this.i = i;
        }
    }

    class BlankFinal{
        /**
         * 直接初始化的final修饰数据
         */
        private final int i = 0;

        /**
         * 未初始化
         */
        private final int j;

        private final Poppet poppet;

        /**
         * 未初始化的final修饰对象必须在构造器中初始化，否则会报错
         */
        public BlankFinal() {
            j = 1;
            poppet = new Poppet(1);
        }

        public BlankFinal(int j, Poppet poppet) {
            this.j = j;
            this.poppet = poppet;
        }
    }

    /**
     * final修饰参数：
     *      Java允许在参数列表中以声明的方式将参数指明为final。这意味着你无法在方法中修改参数引用所指向的对象。
     */
    class FinalArguments{
        /**
         * 试图修改final修饰的参数的引用时，提示错误信息
         * @param poppet
         */
        void with(final Poppet poppet, final int i){
//            poppet = new Poppet(1); //Cannot assign a value to final variable 'poppet'
//            i ++; //Cannot assign a value to final variable 'i'
        }

        void without(Poppet poppet, int i){
            poppet = new Poppet(1);
            i ++;
        }
    }

    /**
     * final修饰方法：
     *      final修饰的方法不能被继承类覆盖
     */

    /**
     * final修饰类：
     *      final修饰的类不能被继承
     */
}
