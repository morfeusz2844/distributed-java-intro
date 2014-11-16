package pl.patrykaugustyn.charityfleamarket;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chairman implements Runnable {

    private final String threadName = "Chairman";
    private volatile static boolean isAuctionAvailable;
    private ReentrantLock lock = new ReentrantLock();

    private static Queue<Item> itemsAuctionQueue;
    private static List<Recipent> registeredRecipents;

    public Chairman() {
        super();
        System.out.println("Create " + threadName);
        this.itemsAuctionQueue = new LinkedList<>();
        this.registeredRecipents = new ArrayList<>();
        this.isAuctionAvailable = true;
    }

    public static int getCountAuction() {
        return itemsAuctionQueue.size();
    }

    public static boolean getAuctionAvailable() {
        return isAuctionAvailable;
    }

    public static void addItemToAuctionQueue(Item item) {
        itemsAuctionQueue.add(item);
    }

    public static int getRegisteredRecipentsNumber() {
        return registeredRecipents.size();
    }

    public static void addRecipentToAuction(Recipent item) {
        registeredRecipents.add(item);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);

        while (MarketManager.getIsMarketOpen()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MarketManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (itemsAuctionQueue.size() == 0) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MarketManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (itemsAuctionQueue.size() == 0) {
                System.out.println("No auction within 5 seconds. Closing the market.");
                isAuctionAvailable = false;
                MarketManager.setIsMarketOpen(false);
                break;
            }
            Item item = itemsAuctionQueue.element();
            itemsAuctionQueue.remove();

            if (registeredRecipents.isEmpty()) {
                addItemToAuctionQueue(item);
                System.out.println("There is no winner for " + item.getItemName());
            } else {
                lock.lock();
                try {
                    int i = (int) util.RandomLong(0, (long) registeredRecipents.size());
                    registeredRecipents.get(i).addWonItem(item);

                    System.out.println("Winner for auction " + item.getItemName() + " is " + registeredRecipents.get(i).getName());

                    for (Recipent recipent : registeredRecipents) {
                        recipent.setIsRegistered(false);

                    }
                    registeredRecipents.clear();
                } finally {
                    lock.unlock();
                }

            }
        }
        MarketManager.setIsMarketOpen(false);

        System.out.println("Stoped " + threadName);
    }

}
