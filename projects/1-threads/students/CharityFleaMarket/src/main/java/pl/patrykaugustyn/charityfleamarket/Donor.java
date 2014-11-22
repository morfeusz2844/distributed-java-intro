package pl.patrykaugustyn.charityfleamarket;

import static java.lang.Thread.sleep;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Donor implements Runnable {

    private String name;
    private boolean isItemRegistered;
    
    private Queue<Item> listItems;

    public Donor(String name, long timeInterval) {
        super();
        System.out.println("Creating " + name);
        this.name = name;
        this.isItemRegistered = false;
                this.listItems = new LinkedList<>();
        this.createItemQueue();
    }
    private void createItemQueue() {
        Random randomGenerator = new Random();
        int numberItem = randomGenerator.nextInt(100);
        for (int i = 0; i < numberItem; i++) {
            this.listItems.add(new Item(name + "-item-" + i));
        }
        System.out.println(name + " generate items " + numberItem);
    }
    private synchronized void registerItem() {
        if (this.listItems.size() != 0) {
            if (Chairman.getCountAuction() > 9) {
                try {
                    sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Donor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (Chairman.getCountAuction() < 10) {
                if (this.listItems.size() > 0) {
                    Item item = this.listItems.element();
                    Chairman.addItemToAuctionQueue(item);
                    this.listItems.remove(item);
                }
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Register " + name);
        while (MarketManager.getIsMarketOpen()) {
            long time = util.RandomLong(1000, 30000);
            try {
                sleep(time);
            } catch (InterruptedException ex) {
                Logger.getLogger(Donor.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Register item to Queue
            this.registerItem();
        }
        System.out.println("Close " + name);
    }
}
