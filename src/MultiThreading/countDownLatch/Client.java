package MultiThreading.countDownLatch;


import java.util.concurrent.CountDownLatch;

/**
 * Created by Daisy on 2/25/16.
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        Waiter waiter = new Waiter(latch);
        Decrementer decrementer = new Decrementer(latch);

        new Thread(waiter).start();
        new Thread(decrementer).start();

        Thread.sleep(4000);
    }
}
