package MultiThreading.blockingQueue;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by Daisy on 2/25/16.
 */
public class SynchronousQueueTester {
    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueue<String> syncQueue = new SynchronousQueue<String>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println(syncQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // synchronousQueue can contain only one element internally. a put to the queue will be blocked until take is
        // happening.
        // while a take on a empty queue is blocked until the next put.
        syncQueue.put("daisy");
        System.out.println("end of put");
    }
}
