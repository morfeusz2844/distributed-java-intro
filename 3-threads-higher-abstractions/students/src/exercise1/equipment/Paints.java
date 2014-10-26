package exercise1.equipment;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Paints {

    private int available = 3;
    final ReentrantLock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    public void takePaint() throws InterruptedException {
        try {
            lock.lock();
            available -= 1;
        } catch (IllegalStateException e) {
            throw new IllegalStateException("There are no more paints!");
        } finally {
            lock.unlock();
        }
    }

    public void returnPaint() {
        lock.lock();
        try {
            available += 1;
            condition.signal();
        }
        finally{
            lock.unlock();
        }
    }
}
