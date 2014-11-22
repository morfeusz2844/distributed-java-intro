package pl.patrykaugustyn.charityfleamarket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MarketManager implements Runnable {
    
    private final String threadName = "MarketManager";
    private volatile static boolean isMarketOpen;

    private final static int numberDonors = 5;
    private final static int numberRecipients = 15;

    private List<Donor> donorsList;
    private List<Recipent> recipentsList;

    private ExecutorService donorsExecutor;
    private ExecutorService recipientsExecutor;
    private ExecutorService chairmanExecutor;

    public MarketManager() {
        super();
        System.out.println("Creating " + threadName);
        donorsList = new ArrayList<>();
        recipentsList = new ArrayList<>();
        donorsExecutor = Executors.newCachedThreadPool();
        recipientsExecutor = Executors.newCachedThreadPool();
        chairmanExecutor = Executors.newCachedThreadPool();
        isMarketOpen = false;
    }

    public static void setIsMarketOpen(boolean value) {
        isMarketOpen = value;
    }
    public static boolean getIsMarketOpen(){
        return isMarketOpen;
    }

    private void registerDonors() {
        for (int i = 0; i < numberDonors; i++) {
            donorsList.add(new Donor("Donor-" + i, util.RandomLong(5000, 30000)));
            donorsExecutor.execute(donorsList.get(i));
        }
    }

    private void registerRecipents() {
        for (int i = 0; i < numberRecipients; i++) {
            recipentsList.add(new Recipent("Recipent-" + i));
            recipientsExecutor.execute(recipentsList.get(i));
        }
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);

        
        setIsMarketOpen(true);
        this.registerDonors();
        this.registerRecipents();
        Chairman chairman = new Chairman();
        chairmanExecutor.execute(chairman);

        chairmanExecutor.shutdown();
        donorsExecutor.shutdown();
        recipientsExecutor.shutdown();

    }

}
