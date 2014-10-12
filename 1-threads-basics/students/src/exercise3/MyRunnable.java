package exercise3;


public class MyRunnable implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName());
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        System.out.println("FINISHED");
    }
}
