package com.el.gov.interview.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Jiangkui
 * @since 2019/11/28 15:32
 */
public class LockSupportUtils {




    static class LockObj {

        private boolean park() {
            System.out.println(Thread.currentThread().getName() + "blocker");
            LockSupport.park(this);
            System.out.println(Thread.currentThread().getName() + "un_blocker");
            return Thread.interrupted();
        }

        private  boolean unPark(Thread thread) {
            LockSupport.unpark(thread);
            System.out.println(Thread.currentThread().getName() + "un_pack_blocker");
            return Thread.interrupted();
        }
    }


    public static void main(String[] args) {
//        LockObj lockObj = new LockObj();
//        Thread thread_0 = new Thread(lockObj::park);
//        Thread thread_1 = new Thread(() -> lockObj.unPark(thread_0));
//        thread_0.start();
//        thread_1.start();

        LockSupport.park();
        System.out.println("args = [" + "111111111111" + "]");


    }
}
