package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceExample {

    class HandleFuture<Integer> implements Callable<Integer> {
        private final Integer num;
        public HandleFuture(Integer num) {
            this.num = num;
        }
        @Override public Integer call() throws Exception {
            Thread.sleep(3 * 100);
            System.out.println(Thread.currentThread().getName());
            return num;
        }
    }

    public static void futureTest() throws InterruptedException, ExecutionException {
        System.out.println("main Thread begin:");
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Integer>> result = new ArrayList<Future<Integer>>();
        for (int i = 0; i < 10; i++) {
            CompletionServiceExample completionService = new CompletionServiceExample();
            Future<Integer> submit = executor.submit(completionService.new HandleFuture(i));
            result.add(submit);
        }
        executor.shutdown();
        for (int i = 0; i < 10; i++) {//一个一个等待返回结果
            System.out.println("返回结果：" + result.get(i).get());
        }
        System.out.println("main Thread end:");
    }

    public static void compleTest() throws InterruptedException, ExecutionException {
        System.out.println("main Thread begin:");
        ExecutorService executor = Executors.newCachedThreadPool();
        // 构建完成服务
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);
        for (int i = 0; i < 10; i++) {
            CompletionServiceExample completionService1 = new CompletionServiceExample();
            completionService.submit(completionService1.new HandleFuture(i));
        }
        for (int i = 0; i < 10; i++) {//一个一个等待返回结果
            System.out.println("返回结果：" + completionService.take().get());
        }
        System.out.println("main Thread end:");
    }

    public static void main(String[] arg) throws ExecutionException, InterruptedException {
        CompletionServiceExample.compleTest();
//        CompletionServiceExample.futureTest();
    }
}