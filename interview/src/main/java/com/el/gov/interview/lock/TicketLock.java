package com.el.gov.interview.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jiangkui
 * @since 2019/11/28 16:14
 */
public class TicketLock extends ReentrantLock {
    private AtomicInteger serviceNum = new AtomicInteger(0);
    private AtomicInteger ticketNum = new AtomicInteger(0);
    private final ThreadLocal<Integer> myNum = new ThreadLocal<>();

    @Override
    public void lock() {
        myNum.set(ticketNum.getAndIncrement());
        while (serviceNum.get() != myNum.get()) {
        }
    }

    @Override
    public void unlock() {
        serviceNum.compareAndSet(myNum.get(), myNum.get() + 1);
        myNum.remove();
    }
}
