package MultiThreading.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Daisy on 2/29/16.
 */
public class AtomicBooleanTester {
    public static void main(String[] args) {
        IMyLock lock = new MyLock();
//        IMyLock lock = new MyLockSynchronizedBlock();
        new MyThread(lock).start();
        new MyThread(lock).start();
    }
}

class MyLock implements IMyLock {
    private AtomicBoolean locked = new AtomicBoolean(false);

    public boolean lock() throws InterruptedException {
        Thread.sleep(1000);
        return locked.compareAndSet(false, true);
    }
}

class MyLockSynchronizedBlock implements IMyLock {

    private boolean locked = false;

    public synchronized boolean lock() throws InterruptedException {
        if (!locked) {
            Thread.sleep(1000);
            locked = true;
            return true;
        }
        return false;
    }
}

interface IMyLock {
    boolean lock() throws InterruptedException;
}

class MyThread extends Thread {
    IMyLock lock;

    MyThread(IMyLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread() + " " + lock.lock());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}