package pl.patrykaugustyn.charityfleamarket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CharityFleaMarket {
    public static void main(String[] args) {
        ExecutorService marketExecutor = Executors.newCachedThreadPool();
        MarketManager marketManager = new MarketManager();
        marketExecutor.execute(marketManager);
        marketExecutor.shutdown();
//        while (!marketExecutor.isTerminated()){}
//        System.out.println("Market closed");
    }
}
