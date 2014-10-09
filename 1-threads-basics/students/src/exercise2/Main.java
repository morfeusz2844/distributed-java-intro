package exercise2;

public class Main {

    public static void main(String[] args) {

        MyThread[] test = new MyThread[4];
        for (MyThread x:test)
        {
            x.run();
        }
        
    }
}
