package MultiThreading.waitNNotify;


import java.util.concurrent.locks.Lock;

/**
 * Created by Daisy on 2/23/16.
 */
public class ClientSelfWait {
    public static void main(String[] args) throws InterruptedException {
        ClientSelfWait client = new ClientSelfWait();
//        Lock lock
        new WaitTerminator(client).start();
        new Waiter(client).start();
        synchronized (client) {
            System.out.println("main waiting");
            client.wait();
        }
        System.out.println("end of main");
    }
}

class WaitTerminator extends Thread {
    ClientSelfWait client;

    WaitTerminator(ClientSelfWait client) {
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println(this.getClass().getSimpleName() + " running");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (client) {
            client.notify();
        }
    }
}

class Waiter extends Thread {
    ClientSelfWait client;

    Waiter(ClientSelfWait client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println(Waiter.class.getSimpleName() + " started waiting");
            synchronized (client) {
                client.wait();
            }
            System.out.println(Waiter.class.getSimpleName() + " ended waiting");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
