package cn.ply.cloud.java.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/16
 * @ModifiedBy
 */
public class ThreadBaseInfo {
    /**
     * 线程的状态
     * NEW 初始状态
     * RUNNABLE 运行状态
     * BLOCKED 阻塞状态
     * WAITING 等待状态
     * TIME_WAITING 超时等待状态
     * TERMINATED 中止状态
     */
    /**
     * synchronize的锁升级
     * 偏向所锁，轻量级锁，重量级锁
     * 偏向所锁，轻量级锁都是乐观锁，重量级锁是悲观锁。
     * 一个对象刚开始实例化的时候，没有任何线程来访问它的时候,它是可偏向的。
     * 意味着，它现在认为只可能有一个线程来访问它，所以当第一个
     * 线程来访问它的时候，它会偏向这个线程，此时，对象持有偏向锁。偏向第一个线程，这个线程在修改对象头成为偏向锁的时候使用CAS操作，并将
     * 对象头中的ThreadID改成自己的ID，之后再次访问这个对象时，只需要对比ID，不需要再使用CAS在进行操作。
     * 一旦有第二个线程访问这个对象，因为偏向锁不会主动释放，所以第二个线程可以看到对象时偏向状态，这时表明在这个对象上已经存在竞争了，
     * 检查原来持有该对象锁的线程是否依然存活，如果挂了，则可以将对象变为无锁状态，然后重新偏向新的线程，如果原来的线程依然存活，
     * 则马上执行那个线程的操作栈，检查该对象的使用情况，如果仍然需要持有偏向锁，则偏向锁升级为轻量级锁，（偏向锁就是这个时候升级为轻量级锁的）。
     * 如果不存在使用了，则可以将对象回复成无锁状态，然后重新偏向。
     * 轻量级锁认为竞争存在，但是竞争的程度很轻，一般两个线程对于同一个锁的操作都会错开，或者说稍微等待一下（自旋），另一个线程就会释放锁。
     * 但是当自旋超过一定的次数，或者一个线程在持有锁，一个在自旋，又有第三个来访时，轻量级锁膨胀为重量级锁，重量级锁使除了拥有锁的线程以外的线程都阻塞，防止CPU空转。
     */
    /**
     * 锁        优点                                                                  缺点                                          适用场景
     * 偏向锁	加锁和解锁不需要额外的消耗，和执行非同步方法比仅存在纳秒级的差距	如果线程间存在锁竞争，会带来额外的锁撤销的消耗	适用于只有一个线程访问同步块场景
     * 轻量级锁	竞争的线程不会阻塞，提高了程序的响应速度	                        如果始终得不到锁竞争的线程使用自旋会消耗CPU	    追求响应时间,锁占用时间很短
     * 重量级锁	线程竞争不使用自旋，不会消耗CPU	                                    线程阻塞，响应时间缓慢	                        追求吞吐量,锁占用时间较长
     */
    /**
     * 创建线程的方式  继承Thread类  实现Runnable接口  实现Callable接口 线程池
     * 线程池参数
     * corePoolSize 核心线程数，提交任务时，如果线程数小于该值，会直接创建线程执行任务，不论线程池中其他线程是否空闲
     * runnableTaskQueue 任务队列 用于保存等待执行的任务的阻塞队列；常用队列：ArrayBlockingQueue，LinkedBlockingQueue，SynchronousQueue，PriorityBlockingQueue
     * maximumPoolSize 线程最大数量，任务队列满了，已创建线程小于该值，会继续创建新线程执行任务。如果使用了无界任务队列，这个参数就，没有效果
     * ThreadFactory 线程工厂
     * RejectedExecutionHandler 饱和策略； AbortPolicy（默认策略）：直接抛出异常；CallerRunsPolicy：只用调用者所在线程来运行任务； DiscardOldestPolicy：丢弃队列中最近的一个任务并执行当前任务；DiscardPolicy：不处理，直接丢弃；可实现RejectedExecutionHandler接口自动策略；
     * keepAliveTime 线程活动保持时间  工作线程空闲后，保持存活的时间
     * TimeUnit 活动保持时间单位
     *
     * 四种线程池
     */
    /**
     * synchronized  实现
     * 给予监视器（monitor）实现，访问同步块的线程必须先获取同步对象的监视器，获取成功则访问同步块，获取失败则会挂起，等待
     * 其他线程退出监视器。
     *
     * 线程上下文如何切换
     * cpu通过时间片分配算法来循环执行任务，当前任务执行一个时间片后会切换到下一个任务。切换前会保存任务状态，以便切换回来时
     * 恢复任务。保存任务状态到再加载的过程就是上下文切换。
     */


    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 实现Callable接口创建线程
         */
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            Integer integer = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /**
         * 创建线程池  Executors工厂类创建常用线程池
         */
        //固定线程个数线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //单个线程线程池
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        //无界线程池，根据需要创建线程
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        //定时、周期任务线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledExecutorService scheduledExecutorService1 = Executors.newSingleThreadScheduledExecutor();

        int i = 10;
        char x = 'x';
        System.out.println(false ? i : x);
        /**可重入锁
         * 相同线程重复获取锁时，不会被自己阻塞
         * **/
        ReentrantLock reentrantLock = new ReentrantLock();
        new CountDownLatch(1);
        /**读写锁
         * 可多个线程同时获取读锁，读锁被获取时，会阻塞写锁获取
         * 写锁被获取时，会阻塞其他写锁和读锁获取
         * 写锁可降级为读锁，在释放写锁之前获取读锁，再释放写锁，实现锁的降级。
         * 使用降级锁保证写线程的数据对当前线程后续的读操作的可见性。
         * **/
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        readLock.lock();


        /**
         * 拥有两个同步资源的锁
         */
        TwinsLock twinsLock = new TwinsLock();
        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                for (;;) {
                    twinsLock.lock();
                    try {
                        Thread.sleep(1000L);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000L);
                    } catch (Exception e){}finally {
                        twinsLock.unlock();
                    }
                }
            }).start();
        }

        new ConcurrentHashMap<>();
        new HashMap<>();
        new Vector<>();
    }
}
