package cn.ply.cloud.java.thread;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/20
 * @ModifiedBy
 */
public class TwinsLock implements Lock, Serializable {

    private final Sync sync = new Sync(2);

    private static class Sync extends AbstractQueuedSynchronizer {
        public Sync(int count) {
            if (count <= 0){
                throw new  IllegalArgumentException();
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            for (;;){
                int current = getState();
                int newCount = current - arg;
                if (newCount >= 0 && compareAndSetState(current, newCount)){
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (;;) {
                int current = getState();
                int newCount = current + arg;
                if (compareAndSetState(current, newCount)){
                    return true;
                }
            }
        }
    }

    @Override
    public void lock() {
        sync.tryAcquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
