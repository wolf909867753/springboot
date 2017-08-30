package com.test.thread;

import java.text.SimpleDateFormat;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wanglu-jf on 17/8/23.
 */
public class sequence {
    private static final long ONE_STEP = 10;
    private static final Lock LOCK = new ReentrantLock();
    private static long lastTime = System.currentTimeMillis();
    private static short lastCount = 0;
    private static int count = 0;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    public static String nextId(){
        LOCK.lock();
        try {
            if (lastCount == ONE_STEP) {
                boolean done = false;
                while (!done) {
                    long now = System.currentTimeMillis();
                    if (now == lastTime) {
                        try {
                            Thread.currentThread();
                            Thread.sleep(1);
                        } catch (java.lang.InterruptedException e) {
                        }
                        continue;
                    } else {
                        lastTime = now;
                        lastCount = 0;
                        done = true;
                    }
                }
            }
            count = lastCount++;
        }
        finally{
            LOCK.unlock();
            return lastTime+""+String.format("%04d",count);
        }
    }

    public static void main(String[] args){
        //测试
        for(int i=0;i<1000;i++)
        {
            System.out.println(nextId());
        }
    }
}
