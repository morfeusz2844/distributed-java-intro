package pl.edu.amu.dji.jms.lab4;

import java.util.ArrayList;

public class Products {

    private ArrayList<Item> products;

    public Products() {
        this.products = new ArrayList<Item>();
    }
    public void setProductList(ArrayList<Item> prod){
        this.products = prod;
    }
    public void addToProductsList(Item item){
        this.products.add(item);
    }
    public ArrayList<Item> getProductsList(){
        return this.products;
    }
}
