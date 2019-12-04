package com.el.gov.interview.lock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jiangkui
 * @since 2019/11/28 16:13
 */
public class SpinLock extends ReentrantLock {
    /**
     *  use thread itself as  synchronization state
     *  使用Owner Thread作为同步状态，比使用一个简单的boolean flag可以携带更多信息
     */
    private AtomicReference<Thread> owner = new AtomicReference<>();
    /**
     * reentrant count of a thread, no need to be volatile
     */
    private int count = 0;

    @Override
    public void lock() {
        Thread t = Thread.currentThread();
        // if re-enter, increment the count.
        if (t == owner.get()) {
            ++count;
            return;
        }
        //spin
        while (owner.compareAndSet(null, t)) {
        }
    }

    @Override
    public void unlock() {
        Thread t = Thread.currentThread();
        //only the owner could do unlock;
        if (t == owner.get()) {
            if (count > 0) {
                // reentrant count not zero, just decrease the counter.
                --count;
            } else {
                // compareAndSet is not need here, already checked
                owner.set(null);
            }
        }
    }
}
