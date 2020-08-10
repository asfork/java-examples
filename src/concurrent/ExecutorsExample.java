package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsExample {
    public static void main(String[] args) {
        // ExecutorService pool = Executors.newFixedThreadPool(5);
        // ExecutorService pool = Executors.newSingleThreadExecutor();
        ExecutorService pool = Executors.newCachedThreadPool();
        // ExecutorService pool = Executors.newWorkStealingPool();
        // ExecutorService pool = Executors.newScheduledThreadPool(3);
        try {
            for (int i = 0; i < 10; i++) {
                pool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t do something");
                    try {
                        TimeUnit.MICROSECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t done");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }
}