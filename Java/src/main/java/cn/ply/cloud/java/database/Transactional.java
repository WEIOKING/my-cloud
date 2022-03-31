package cn.ply.cloud.java.database;

/**
 * @Author ply
 * @Description 事务
 * @Date created in 2022/3/23
 * @ModifiedBy
 */
public class Transactional {
    /**
     * 事务的特性
     * 原子性：强调事务的不可分割.
     * 一致性:事务的执行的前后数据的完整性保持一致
     * 隔离性:一个事务执行的过程中,不应该受到其他事务的干扰
     * 持久性:事务一旦结束,数据就持久到数据库
     */
    /**
     * 脏读：一个事务读到了另一个事务的未提交的数据
     * 不可重复读：在一个事务中，多次对同一数据访问，事务读到了另一个事务已经提交的 update 的数据导致多次查询结果不一致
     * 幻读：:一个事务读到了另一个事务已经提交的 insert 的数据导致多次查询结果不一致
     */
    /**
     * 事务的隔离级别
     * DEFAULT 这是一个PlatfromTransactionManager默认的隔离级别，使用数据库默认的事务隔离级别.
     *
     * Mysql 默认:可重复读
     *
     * Oracle 默认:读已提交
     *
     * READ_UNCOMMITTED （未提交读）：是最低的事务隔离级别，它允许另外一个事务可以看到这个事务未提交的数据。
     *
     * READ_COMMITTED（已提交读）：保证一个事物提交后才能被另外一个事务读取。另外一个事务不能读取该事物未提交的数据。
     *
     * REPEATABLE_READ（可重复读）：这种事务隔离级别可以防止脏读，不可重复读。但是可能会出现幻象读。它除了保证一个事务不能被另外一个事务读取未提交的数据之外还避免了以下情况产生（不可重复读）。
     *
     * SERIALIZABLE（串行化）：这是花费最高代价但最可靠的事务隔离级别。事务被处理为顺序执行。除了防止脏读，不可重复读之外，还避免了幻象读（避免三种）。
     */

    /**
     * 事务的传播行为
     * PROPAGION_XXX :事务的传播行为
     *
     * * 保证同一个事务中：
     * PROPAGATION_REQUIRED 支持当前事务，如果不存在 就新建一个(默认)
     *
     * PROPAGATION_SUPPORTS 支持当前事务，如果不存在，就不使用事务
     *
     * PROPAGATION_MANDATORY 支持当前事务，如果不存在，抛出异常
     *
     *
     ** 保证没有在同一个事务中：
     * PROPAGATION_REQUIRES_NEW 如果有事务存在，挂起当前事务，创建一个新的事务
     *
     * PROPAGATION_NOT_SUPPORTED 以非事务方式运行，如果有事务存在，挂起当前事务
     *
     * PROPAGATION_NEVER 以非事务方式运行，如果有事务存在，抛出异常
     *
     * PROPAGATION_NESTED 如果当前事务存在，则嵌套事务执行
     */
    /**
     * 另外，事务常用的两个属性：readonly和timeout
     * 一个是设置事务为只读以提升性能。
     * 另一个是设置事务的超时时间，一般用于防止大事务的发生。还是那句话，事务要尽可能的小！
     */
}
