package exercise2.equipment;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Paints {
    
    private final BlockingQueue queue;
    public Paints(){
        this.queue = new ArrayBlockingQueue<String>(1024);
        this.queue.offer("Red");
        this.queue.offer("Yellow");
        this.queue.offer("Red");
    }
    public String takePaint() throws InterruptedException {
        return (String) this.queue.take();
    }

    public void returnPaint(String paint) {
        this.queue.offer(paint);
    }
}
