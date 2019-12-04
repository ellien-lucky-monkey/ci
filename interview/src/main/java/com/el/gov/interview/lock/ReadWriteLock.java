package com.el.gov.interview.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Jiangkui
 * @since 2019/11/29 09:41
 */
public class ReadWriteLock {


    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    /**
     * 读锁
     */
    private Lock r = reentrantReadWriteLock.readLock();

    /**
     * 写锁
     */
    private Lock w = reentrantReadWriteLock.writeLock();

    /**
     * 执行线程池
     */
    private ExecutorService executorService = Executors.newCachedThreadPool();


//    public static void main(String[] args) {
//
//    }

    public void testReadLock() {
        for (int i = 0; i < 10; i++) {
            Thread readWorker = new ReadWorker();
            executorService.submit(readWorker);
        }
        waitForExecutorFinish();
    }


    public void testWriteLock() {
        for (int i = 0; i < 10; i++) {
            Thread writeWorker = new WriteWorker();
            executorService.submit(writeWorker);
        }
        waitForExecutorFinish();
    }

    public void testReadWriteLock() {
        for (int i = 0; i < 10; i++) {
            Thread readWorker = new ReadWorker();
            Thread writeWorker = new WriteWorker();
            executorService.submit(readWorker);
            executorService.submit(writeWorker);
        }
        waitForExecutorFinish();
    }

    /**
     * 线程模拟完成后，关闭线程池
     */
    private void waitForExecutorFinish() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class ReadWorker extends Thread {

        @Override
        public void run() {
            r.lock();
            try {
                Thread.sleep(5);
                System.out.println(System.currentTimeMillis() + ": " + Thread.currentThread().getName() + " reading...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                r.unlock();
            }
        }
    }

    class WriteWorker extends Thread {

        @Override
        public void run() {
            w.lock();
            try {
                Thread.sleep(5);
                System.out.println(System.currentTimeMillis() + ": " + Thread.currentThread().getName() + " writing...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                w.unlock();
            }
        }
    }

    class Animal {

        private int count = 1;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }


}
