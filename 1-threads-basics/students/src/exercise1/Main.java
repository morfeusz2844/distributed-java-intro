package exercise1;

import java.lang.Thread;

public class Main {

    public static String getThreadInfo()
    {
        StringBuilder sb = new StringBuilder();
        String name = Thread.currentThread().getName();
        long id = Thread.currentThread().getId();
        sb.append("Name=").append(name);
        sb.append(",ID=").append(id);
        return sb.toString();
    }
    public static void main(String[] args) {
       System.out.println(getThreadInfo());
    }
}
