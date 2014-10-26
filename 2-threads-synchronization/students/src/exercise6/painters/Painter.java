package exercise6.painters;

import exercise6.equipment.Brush;
import exercise6.equipment.Paint;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Painter implements Runnable {

    protected final Paint paint;
    protected final Brush brush;
    protected final ReentrantLock lock = new ReentrantLock();

    public Painter(Paint paint, Brush brush) {
        this.paint = paint;
        this.brush = brush;
    }
}
