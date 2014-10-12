package exercise2;

public class MyThread extends Thread {


    MyThread(String name) {
        super.setName(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try
            {
                Thread.sleep(300);
                System.out.println(Thread.currentThread().getName());
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            
        }

    }
}
