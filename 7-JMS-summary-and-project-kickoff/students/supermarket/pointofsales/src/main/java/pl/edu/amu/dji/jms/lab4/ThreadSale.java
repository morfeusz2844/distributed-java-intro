package pl.edu.amu.dji.jms.lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadSale implements Runnable {

    private Boolean isRunning;
    private ArrayList<Item> products;
    private AnalysisServiceProducer asp;

    public ThreadSale(ArrayList<Item> products, AnalysisServiceProducer asp) {
        this.isRunning = false;
        this.products = products;
        this.asp = asp;
    }

    public Boolean getIsRunning() {
        return isRunning;
    }

    public ArrayList<Item> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Item> products) {
        this.products = products;
    }

    private void sell(String nazwa) {
        for (Item item : this.products) {
            if (item.getName().equals(nazwa)) {
                System.out.println("Sell product: " + item.getName() + " in price: " + item.getPrice());
                asp.sendAnalysisInfo(item);
                break;
            }
        }
    }

    private void startUI() {
        System.out.println("---Sale application---");
        System.out.println("---Enter: EXIT to exit application---");
        System.out.println("---Enter: SALE to sale product---");
        System.out.print(">>");
    }

    private void salingUI(BufferedReader reader) {
        String line = "";
        try {
            System.out.println("---Name product:");
            System.out.print(">>");
            line = reader.readLine();
            this.sell(line);
        } catch (IOException ex) {
            Logger.getLogger(ThreadSale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        this.isRunning = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (this.isRunning) {
            this.startUI();
            try {
                String line = reader.readLine();
                switch (line) {
                    case ("EXIT"): {
                        this.isRunning = false;
                        break;
                    }
                    case ("SALE"): {
                        this.salingUI(reader);
                        break;
                    }
                    default: {
                        System.out.println("Unknow expression");
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ThreadSale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
