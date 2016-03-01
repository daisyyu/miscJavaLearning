package MultiThreading.waitNNotify;

class MonitorObject {
}

public class MyWaitNotify {

    Object lock = new Object();

    public void doWait() {
        synchronized (lock) {
            try {
                lock.wait();
                System.out.println("wait is started");
            } catch (InterruptedException e) {
            }
        }
    }

    public void doNotify() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}