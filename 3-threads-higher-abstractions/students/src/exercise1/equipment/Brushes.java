package exercise1.equipment;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Brushes {
    private int available = 3;
    final ReentrantLock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    public void takeBrush() throws InterruptedException {
        lock.lock();
        try{    
            available -=1;
        }
        catch (IllegalStateException e){
            throw new IllegalStateException("There are no more brushes!");
        }
        finally{
            lock.unlock();
        }
    }

    public void returnBrush() {
        lock.lock();
        try {
            available += 1;
            condition.signal();
        } finally {
            lock.unlock();
        }
        
    }
}
