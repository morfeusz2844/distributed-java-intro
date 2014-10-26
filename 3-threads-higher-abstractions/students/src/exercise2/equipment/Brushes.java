package exercise2.equipment;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Brushes {
    private final BlockingQueue queue;
    public Brushes(){
        this.queue = new ArrayBlockingQueue<String>(1024);
        this.queue.offer("regular");
        this.queue.offer("tringular");
        this.queue.offer("spectacular");
    }
    public String takeBrush() throws InterruptedException {
        return (String) this.queue.take();
    }

    public void returnBrush(String brush) {
        this.queue.offer(brush);
    }
}
