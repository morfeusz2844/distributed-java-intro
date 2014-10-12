package exercise4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService test = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            test.execute(new MyRunnable());
        }
        test.shutdown();
        test.awaitTermination(50, TimeUnit.SECONDS);
        System.out.println("FINISHED ALL");
    }
}
