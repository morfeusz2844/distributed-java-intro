package pl.patrykaugustyn.charityfleamarket;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Donor implements Runnable {

    private String name;
    private boolean isItemRegistered;
    private Item item;
    private long timeInterval; //czas po którym da się rejestrować item

    public Donor(String name, long timeInterval) {
        super();
        System.out.println("Creating " + name);
        this.name = name;
        this.isItemRegistered = false;
        this.item = new Item("item-" + name);
        this.timeInterval = timeInterval;
    }

    public synchronized boolean registerItem() {
        if (Chairman.getCountAuction() < 10) {
            Chairman.addItemToAuctionQueue(this.item);
            this.isItemRegistered = true;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run() {
        System.out.println("Starting " + name);
        try {
            Thread.sleep(this.timeInterval);
        } catch (InterruptedException ex) {
            Logger.getLogger(Donor.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (MarketManager.getIsMarketOpen()) {
            while (!(this.isItemRegistered)) {
                try {
                    if (!(registerItem())) {
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Donor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        System.out.println(name + " says good bye");
    }
}
