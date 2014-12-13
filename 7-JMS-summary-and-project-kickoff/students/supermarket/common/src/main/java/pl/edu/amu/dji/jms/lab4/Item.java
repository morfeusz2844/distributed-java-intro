package pl.edu.amu.dji.jms.lab4;

import java.io.Serializable;

public class Item implements Serializable {

    private final String name;
    private Double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(Double price){
        this.price = price;
    }
    

}
