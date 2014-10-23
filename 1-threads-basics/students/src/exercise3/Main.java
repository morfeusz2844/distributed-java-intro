package exercise3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        MyRunnable rr = new MyRunnable();
        Thread[] th = new Thread[4];
        int i = 0;
        for (Thread item : th) {
            item = new Thread(rr, "Thread-" + i);
            th[i] = item;
            item.start();

            i++;
        }
        for (Thread item : th) {
            try {
                item.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
