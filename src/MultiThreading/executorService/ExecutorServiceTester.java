package MultiThreading.executorService;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by Daisy on 2/28/16.
 */
public class ExecutorServiceTester {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Asynchronous task");
            }
        });
        System.out.println("end of runnable submission");
        // future.get() will block the current Thread until a runnale returns
        System.out.println("result = " + (future.get() == null));
        System.out.println("end of print future result");
        // shut down won't cause the program to exit immediately. It will hault the execution of queued up threads
        // and wait for the current running thread to exit before shut down.
        executorService.shutdown();
    }
}
