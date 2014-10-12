package exercise2;

import java.lang.Thread;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

        Thread[] th = new Thread[4];
        int i=0;
        for (Thread item: th)
        {
            item = new MyThread("Thread"+i);
            item.start();
            i++;
        }
        
    }
}
