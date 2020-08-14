package concurrent;

public class ThreadExample {
    private int x = 0;

    public static void main(String[] arg) {
//        Runnable runnable = new Runnable() {
//            final ThreadExample threadExample = new ThreadExample();
//            @Override public void run() {
//                threadExample.count();
////                new ThreadExample().count();
//            }
//        };

        for (int i = 0; i < 10; i++) {
//            new Thread(runnable).start();
            new Thread(new Runnable() {
                final ThreadExample threadExample = new ThreadExample();
                @Override
                public void run() {
                    threadExample.count();
                }
            }).start();
        }
    }

    public void count() {
        for (int i = 0; i < 10; i++) {
            x += i;
        }
        System.out.println(Thread.currentThread().getName() + "--" + x);
    }
}