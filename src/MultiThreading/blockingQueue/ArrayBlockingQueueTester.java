package MultiThreading.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Daisy on 2/25/16.
 */
public class ArrayBlockingQueueTester {
    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(1024);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    blockingQueue.put("daisy");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // empty blocking Queue take will be blocked indefinately until an element is provided via put
        System.out.println(blockingQueue.take());
        System.out.println("end of take");
    }
}
