package pl.patrykaugustyn.charityfleamarket;

import java.util.Random;

public class util {
    public static long RandomLong(long min, long max){
		Random r = new Random();
		return min+((long)(r.nextDouble()*(max-min)));
	}
}
