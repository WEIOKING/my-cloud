package cn.ply.cloud.java.exception;

/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/26
 * @ModifiedBy
 */
public class BaseInfo {
    /**
     * 异常的分类
     * Throwable作为所有异常的超类
     *
     * Java语言设计者将异常划分为两类：Error和Exception
     * 1、Error（错误）：是程序中无法处理的错误，表示运行应用程序中出现了严重的错误。此类错误一般表示代码运行时JVM出现问题。
     * 通常有Virtual MachineError（虚拟机运行错误）、NoClassDefFoundError（类定义错误）等。
     * 比如说当jvm耗完可用内存时，将出现OutOfMemoryError。此类错误发生时，JVM将终止线程。
     * 这些错误是不可查的，非代码性错误。因此，当此类错误发生时，应用不应该去处理此类错误。
     * 2、Exception（异常）：程序本身可以捕获并且可以处理的异常。
     *
     * Exception这种异常又分为两类：运行时异常和编译异常。
     * 1、运行时异常(不受检异常)：RuntimeException类极其子类表示JVM在运行期间可能出现的错误。
     * 比如说试图使用空值对象的引用（NullPointerException）、数组下标越界（ArrayIndexOutBoundException）。
     * 此类异常属于不可查异常，一般是由程序逻辑错误引起的，在程序中可以选择捕获处理，也可以不处理。
     * 2、编译异常(受检异常)：Exception中除RuntimeException极其子类之外的异常。
     * 如果程序中出现此类异常，比如说IOException，必须对该异常进行处理，否则编译不通过。
     * 在程序中，通常不会自定义该类异常，而是直接使用系统提供的异常类。
     */
}
