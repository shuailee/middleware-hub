package com.shuailee.study.basicknowledge.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 多线程 - 售票例子
 * <p>
 * 通过实现Runnable接口创建多线程：
 * 定义Runnable接口的实现类，并重写该接口的run()方法，该run()方法的方法体同样是该线程的线程执行体
 * 需要注意的是：Runnable对象仅仅作为Thread对象的target，Runnable实现类里包含的run()方法仅作为线程执行体。
 * 而实际的线程对象依然是Thread实例，只是该Thread线程负责执行其target的run()方法
 */
public class ThreadRunnableSyncDemo implements Runnable {
    private volatile int ticket = 10;

    private final byte[] LOCKOBJ = new byte[0];

    /**
     * 不能直接用 synchronized 来修饰 run() 方法，因为如果这样做，那么就会总是第一个线程进入其中，而这个线程执行完所有操作，即卖完所有票了才会出来
     实例方法加锁（同步方法）：作用于当前实例加锁，进入同步代码前要获得当前实例的锁
     静态方法加锁：作用于当前类对象加锁，进入同步代码前要获得当前类对象的锁
     代码块加锁（同步代码块）：指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
     */
    @Override
    public void run() {
        while (true) {
            synchronized (LOCKOBJ) {
                if (this.ticket > 0) {
                    try {
                        // 执行扣票,如果上面不加锁可能同时有多个线程到此处，然后出现超扣的情况 ticket为负数
                        // 加锁之后保证了互斥性，每次只有一个线程进入改代码区域。保证某一时刻只能有一个线程执行售票功能
                        Thread.sleep(100);
                        this.ticket--;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 卖票：ticket" + this.ticket);
                } else {
                    System.out.println("余票不足");
                    break;
                }
            }
        }
    }

    /**
     * 创建Runnable实现类的实例，并以此实例作为Thread的target来创建Thread对象，该Thread对象才是真正的线程对象
     */
    public static void main(String[] args) {
        ThreadRunnableSyncDemo mt = new ThreadRunnableSyncDemo();

        // 启动3个线程t1,t2,t3(它们共用一个Runnable对象)，这3个线程一共卖10张票！
        Thread t1 = new Thread(mt);
        Thread t2 = new Thread(mt);
        Thread t3 = new Thread(mt);
        t1.start();
        t2.start();
        t3.start();
    }
}

/**
 * 使用lock锁
 */
class ThreadTicketLock {
    /**
     * 库存只有10张票，被3个线程共享
     */
    public static volatile int ticket = 10;
    /**
     * 注意如果是非静态的 他只锁当前对象，每new一次当前对象都会产生一个新锁，等于没用
     * 静态的锁锁的是当前类，多个线程创建多个实例使用同一把锁
     * */
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        /**
         *  开启3个线程售票
         * */
        new Thread(() -> new ThreadTicketLock().show()).start();
        new Thread(() -> new ThreadTicketLock().show()).start();
        new Thread(() -> new ThreadTicketLock().show()).start();

    }

    private void show() {
        //如果要是把锁写循环外边就会导致只有第一个进来的线程在执行卖票了
        while (true) {
            lock.lock();
            try {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                        ticket--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 卖票：ticket" + ticket);
                } else {
                    System.out.println("余票不足");
                    break;
                }
            } finally {
                //lock 必须在 finally 块中释放。否则，如果受保护的代码将抛出异常，锁就有可能永远得不到释放
                lock.unlock();
            }
        }

    }
}