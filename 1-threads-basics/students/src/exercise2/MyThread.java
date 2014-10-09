package exercise2;

public class MyThread extends Thread {
    private String name;
    
    public MyThread(String name) {
        super(name);
        this.name = name;
    }
    @Override
    public void run()
    {
        System.out.println(MyThread.currentThread().getName());
    }
}
