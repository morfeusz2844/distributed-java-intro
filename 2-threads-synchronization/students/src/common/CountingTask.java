package common;

public class CountingTask implements Runnable {

    private final Counter counter;
    private final int numberOfIterations;
    private final Timer time = new Timer();

    public CountingTask(Counter counter, int numberOfIterations) {
        this.counter = counter;
        this.numberOfIterations = numberOfIterations;
    }

    @Override
    public void run() {
        this.time.start();
        for (int i = 0; i < numberOfIterations; ++i) {
            counter.increment();
        }
        this.time.end();
        System.out.println("Time:"+this.time.getTotalTime());
    }
}
