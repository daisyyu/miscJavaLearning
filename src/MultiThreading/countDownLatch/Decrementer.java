package MultiThreading.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class Decrementer implements Runnable {

    CountDownLatch latch = null;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {

        try {
            for (int i = 0; i < 3; i++)
                countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void countDown() throws InterruptedException {
        Thread.sleep(1000);
        latch.countDown();
        System.out.println("Latch count down, remaining:" + latch.getCount());
    }
}