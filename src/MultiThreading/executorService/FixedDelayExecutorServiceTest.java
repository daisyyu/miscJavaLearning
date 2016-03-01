package MultiThreading.executorService;

import java.util.concurrent.*;

/**
 * Created by Daisy on 2/28/16.
 */
public class FixedDelayExecutorServiceTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);
        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("Executed!");
            }
        };
//        ScheduledFuture scheduledFuture =
//                scheduledExecutorService.schedule(callable,
//                        5,
//                        TimeUnit.SECONDS);
        ScheduledFuture scheduledFuture =
                scheduledExecutorService.scheduleAtFixedRate(runnable,
                        5, 1,
                        TimeUnit.SECONDS);

        System.out.println("after");
//        System.out.println("result = " + scheduledFuture.get());

        scheduledExecutorService.shutdown();
    }
}
