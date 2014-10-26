package exercise6.painters;

import exercise6.equipment.Brush;
import exercise6.equipment.Paint;

public class RightHandedPainter extends Painter {

    public RightHandedPainter(Paint paint, Brush brush) {
        super(paint, brush);
    }

    @Override
    public void run() {
        String takenPaint;
        String takenBrush;
        try {
            synchronized (brush) {
                takenBrush = brush.takeBrush();
            }
            Thread.sleep(100);
            synchronized (paint) {
                    takenPaint = paint.takePaint();         
                }
            Thread.sleep(100);
            System.out.printf("Right hand painter painting with %s and %s\n", takenPaint, takenBrush);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
