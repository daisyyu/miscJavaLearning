package MultiThreading.waitNNotify;

/**
 * Created by Daisy on 2/22/16.
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        final MyWaitNotify myWaitNotify = new MyWaitNotify();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myWaitNotify.doWait();
                System.out.println("lalala");
            }
        }).start();
        System.out.println("the wait is over");
        myWaitNotify.doNotify();
    }
}
