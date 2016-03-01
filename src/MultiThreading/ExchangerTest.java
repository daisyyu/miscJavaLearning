package MultiThreading;

import java.util.concurrent.Exchanger;

/**
 * Created by Daisy on 2/28/16.
 */
public class ExchangerTest {
    public static void main(String[] args) throws InterruptedException {
        Exchanger exchanger = new Exchanger();
        // Exchanger can only exchange 2 threads variable's reference at a time.
        // Exchanger awaiting to be exchanged will be waiting
        Object objA = new StringWrapper("A");
        Object objB = new StringWrapper("B");
        Object objC = new StringWrapper("C");
        Object objD = new StringWrapper("D");
        ExchangerRunnable exchangerRunnable1 =
                new ExchangerRunnable(exchanger, objA);

        ExchangerRunnable exchangerRunnable2 =
                new ExchangerRunnable(exchanger, objB);

        ExchangerRunnable exchangerRunnable3 =
                new ExchangerRunnable(exchanger, objC);
        ExchangerRunnable exchangerRunnable4 =
                new ExchangerRunnable(exchanger, objD);

        Thread a = new Thread(exchangerRunnable1);
        a.start();
        Thread b = new Thread(exchangerRunnable2);
        b.start();
        Thread c = new Thread(exchangerRunnable3);
        c.start();
        Thread d = new Thread(exchangerRunnable4);
        d.start();
        d.join();
        a.join();
        b.join();
        c.join();

        System.out.println("-------------");
        System.out.println("A: " + objA);
        System.out.println("B: " + objB);
    }
}

class StringWrapper {
    String s;

    StringWrapper(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }

    @Override
    public boolean equals(final Object obj) {
        StringWrapper tmp = (StringWrapper) obj;
        return tmp.s.equals(this.s);
    }
}

class ExchangerRunnable implements Runnable {

    Exchanger exchanger = null;
    Object object = null;

    public ExchangerRunnable(Exchanger exchanger, Object object) {
        this.exchanger = exchanger;
        this.object = object;
    }

    public void run() {
        try {
            Object previous = this.object;

            this.object = this.exchanger.exchange(this.object);

            System.out.println(
                    Thread.currentThread().getName() +
                            " exchanged " + previous + " for " + this.object
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
