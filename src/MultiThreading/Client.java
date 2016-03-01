package MultiThreading;

/**
 * Created by Daisy on 1/27/16.
 */
public class Client {
    static int counter;

    static void incrementCounter() throws InterruptedException {
        if (counter < 1) {
            Thread.sleep(1000);
            counter++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Color color1 = new Color("green");
        System.out.println(color1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                color1.setColor("purpal");
            }
        }).start();
        new Thread(new ColorChanger(color1)).start();
        incrementCounter();
        Thread.sleep(1000);
        System.out.println(color1);
        System.out.println(counter);
    }

    static class Color {
        String color;

        Color(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        public void setColor(final String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Color: " + this.color;
        }
    }

    static class ColorChanger implements Runnable {
        Color color;

        ColorChanger(Color color) {
            this.color = color;
        }

        @Override
        public void run() {
            System.out.println("change color");
            try {
                incrementCounter();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            color.setColor("red");
        }
    }
}
