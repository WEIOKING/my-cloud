package cn.ply.cloud.java.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:ply
 * @Description:
 * @Date: created in 2019/5/10
 * @Modified By:
 */
public class Lambda {
    public static void main(String[] args) {

        Math sum = (a, b) -> a + b;
        Math sub = (a, b) -> {
            System.out.println("多行语句");
            return a -b;
        };
        Math mul = (Double a, Double b) -> a * b;
        Math div = (a, b) -> a / b;
        Double a = 2.5d;
        Double b = 2.5d;
        System.out.println(a + " + " + b + " = " + sum.operation(a, b));
        System.out.println(a + " - " + b + " = " + sub.operation(a, b));
        System.out.println(a + " * " + b + " = " + mul.operation(a, b));
        System.out.println(a + " / " + b + " = " + div.operation(a, b));
        sum.defaultMe();
        Math.staticMe();

        //内部类方式快速启动线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Normal!!!");
            }
        }).start();

        //Lambda方式快速启动线程
        new Thread(() -> System.out.println("Lambda!!!")).start();

        List<Integer> list = new ArrayList<>();
        List<Integer> integers = Arrays.asList(new Integer[]{1, 2, 3, 4, 5});
        //Lambda内部不能修改外部变量的值
        //Lambda表达式中可以向外部list添加元素
        integers.forEach(integer -> {
//            a = 0d;
            System.out.println(a);
            list.add(mul.operation(Double.valueOf(integer),Double.valueOf(integer)).intValue());
        });
        list.forEach(System.out :: println);

    }

    /**
     * 函数式接口，主要用于Lambda表达式
     * 函数式接口中只允许存在一个抽象方法
     * @FunctionalInterface 注解可检验接口是否是函数式接口
     */
    @FunctionalInterface
    interface Math{
        /**
         * 两个Double值的运算接口方法
         * @param a
         * @param b
         * @return
         */
        Double operation(Double a, Double b);

        /**
         * 函数式接口中允许存在默认方法
         */
        default void defaultMe(){
            System.out.println("default");
        }

        /**
         * 函数式接口中允许存在静态方法
         */
        static void staticMe(){
            System.out.println("static");
        }

        /**
         * 函数式接口允许定义java.lang.Object里的public方法
         * 因为任何一个函数式接口的实现，默认都继承了Object类，包含了来自java.lang.Object里对这些抽象方法的实现
         * @param object
         * @return
         */
        @Override
        boolean equals(Object object);
    }
}
