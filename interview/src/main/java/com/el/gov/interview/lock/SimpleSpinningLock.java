package com.el.gov.interview.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 基于cas的自旋锁
 * @author Jiangkui
 * @since 2019/11/28 16:07
 */
public class SimpleSpinningLock {
    /**
     * 持有锁的线程，null表示锁未被线程持有
     */
    private AtomicReference<Thread> ref = new AtomicReference<>();

    public void lock(){
        Thread currentThread = Thread.currentThread();
        while(!ref.compareAndSet(null, currentThread)){
            //当ref为null的时候compareAndSet返回true，反之为false
            //通过循环不断的自旋判断锁是否被其他线程持有
        }
    }

    public void unLock() {
        Thread cur = Thread.currentThread();
        if(ref.get() != cur){
            //exception ...
        }
        ref.set(null);
    }


    static int count  = 0;
    static ExecutorService executorService = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(100);
        SimpleSpinningLock simpleSpinningLock = new SimpleSpinningLock();
        for (int i = 0 ; i < 100 ; i++){
            executorService.execute(() -> {
                simpleSpinningLock.lock();
                ++count;
                simpleSpinningLock.unLock();
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        System.out.println(count);
    }
}
