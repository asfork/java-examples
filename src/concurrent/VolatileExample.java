package concurrent;

public class VolatileExample {
    public static volatile int found = 0;
    // public static int found = 0;

    public static void change() {
        found = 1;
    }

    public static void main(String[] args) {
        new Thread((Runnable) () -> {
            System.out.println("Wait for target");
            while (0 == found) {
            }
            System.out.println("Target arrived");
        }, "Main thread").start();

        new Thread((Runnable) () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Send signal to main");

            change();
        }, "Target Thread").start();
    }
}