package cn.ply.cloud.spring;

/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/26
 * @ModifiedBy
 */
public class BaseInfo {
    /**
     * 特点
     * 核心组件
     * IOC
     * 在调用一个对象的方法时，不在需要新建或者获取对象的实例，可以直接由容器分配初始化好的实例直接使用。基于反射机制和工厂模式实现
     *
     * AOP 原理
     * 分离关注点，只用关注核心关注点部分，其他的如事务、日志不需关注，使用AOP统一处理；使用动态代理和静态织入实现，这里静态织入的原理就是在编译期间，切面直接以字节码形式编译到目标字节码中
     *
     *spring bean
     * 作用域  @Scope 注解设置作用域 ConfigurableBeanFactory.SCOPE_PROTOTYPE
     * 单例 Singleton（默认）: 在应用中，只创建一个实例
     * 原型 Prototype： 每次注入或通过上下文获取时都创建一个新的实例
     * 会话 Session：在web应用中，为每个会话创建一个实例
     * 请求 Request：在web应用中，为每个请求创建一个实例
     * 全局的 HTTP会话 global Session：在一个全局的 HTTP Session 中，容器会返回该 Bean 的同一个实例。该作用域仅在使用 portlet context 时有效
     *
     * spring循环依赖问题  A依赖A； A依赖B B依赖A；A依赖B B依赖C C依赖A
     * spring使用三级缓存解决循环依赖问题
     * singletonObjects 一级缓存，用于保存实例化、注入、初始化完成的bean实例
     * earlySingletonObjects 二级缓存，用于保存实例化完成的bean实例
     * singletonFactories 三级缓存，用于保存bean创建工厂，以便于后面扩展有机会创建代理对象。
     *
     * 在获取对象实例时，spring首先从一级缓存找，然后从二级缓存寻找，都没有找到时创建bean实例，
     *
     *
     *
     * 生命周期
     *
     * 依赖注入的三种方式
     * 接口注入：
     * 构造器注入：接受注入的类中定义一个构造方法，并在参数中定义需要注入的元素。
     * set方法注入：在接受注入的类中定义一个Set方法，并在参数中定义需要注入的元素。
     *
     * spring MVC 工作流程
     *
     * spring boot
     */
}
