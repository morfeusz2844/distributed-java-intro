package pl.patrykaugustyn.charityfleamarket;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Recipent implements Runnable {

    private String name;
    private boolean isRegistered;
    private List<Item> wonItems;
    private boolean won;

    public Recipent(String name) {
        super();
        System.out.println("Creating " + name);
        this.name = name;
        this.isRegistered = false;
        this.wonItems = new ArrayList<>();
        this.won = false;
    }

    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public String getName() {
        return this.name;
    }

    public void addWonItem(Item item) {
        this.wonItems.add(item);
        this.won = true;
    }

    public synchronized boolean registerToAuction() {
        if (Chairman.getRegisteredRecipentsNumber() < 10 && Chairman.getCountAuction() > 0) {
            this.isRegistered = true;
            Chairman.addRecipentToAuction(this);
            return true;
        } else {
            return false;
        }
    }

    private String getListWonItems() {
        String temp = "";
        for (Item item : wonItems) {
            temp = temp + item.getItemName() + ", ";
        }
        return temp;
    }

    @Override
    public void run() {
        System.out.println("Starting " + name);
        try {
            while (MarketManager.getIsMarketOpen()) {
                if (!(this.isRegistered) && (Chairman.getAuctionAvailable())) {
                    Thread.sleep(5000);
                    if (this.won == true) {
                        this.won = false;
                        Thread.sleep(util.RandomLong(5000, 15000));
                    }
                    if (registerToAuction()) {
                        System.out.println("Registering " + name + ".");
                    }
                } else {
                    Thread.sleep(2000);
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Recipent.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(name + " says good bye leaving with items " + getListWonItems());
    }
}
