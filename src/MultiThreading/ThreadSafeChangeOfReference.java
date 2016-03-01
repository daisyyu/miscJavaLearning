package MultiThreading;

/**
 * Created by Daisy on 1/17/16.
 */
public class ThreadSafeChangeOfReference {
    public static void main(String[] args) throws InterruptedException {
        String test = "Daisy";
        new Thread(new Runnable() {
            @Override
            public void run() {
                // variable test is accessed within a inner class. must be declaired as final.
                // The below assignment will not work.
                // if we choose to create a class implement Runnable interface and pass "test" in as parameter.
                // java will copy the reference when entering method or class constructors.
                // thus reference assignment change within class won't affect actual value.
//                test = "Muted Daisy";
            }
        }).start();
        Thread.sleep(1000);
        System.out.println(test);
    }
}
