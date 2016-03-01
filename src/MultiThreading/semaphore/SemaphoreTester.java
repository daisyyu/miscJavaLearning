package MultiThreading.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.Semaphore;

/**
 * Created by Daisy on 2/24/16.
 */
public class SemaphoreTester {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        List<String> list = new Vector<String>();
        List<Thread> threadList = new ArrayList<Thread>();
        for (int i = 0; i < 8; i++) {
            threadList.add(new MyThread(semaphore, list));
        }
        for (Thread t : threadList) {
            t.start();
        }


    }

}

class MyThread extends Thread {
    Semaphore semaphore;
    String name;
    List<String> list;
    static int counter = 0;

    MyThread(Semaphore semaphore, List list) {
        this.semaphore = semaphore;
        this.list = list;
        synchronized (MyThread.class) {
            name = (counter++) + "";
        }
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            list.add(name);
            System.out.println("Current list size: " + list.size() + " " + list);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
        list.remove(name);
    }
}
