package pl.edu.amu.dji.jms.lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class Products {

    private ArrayList<Item> products;

    public Products() {
        this.products = new ArrayList<>();
        this.loadItems();
    }

    public void setProductList(ArrayList<Item> prod) {
        this.products = prod;
    }

    public void addToProductsList(Item item) {
        this.products.add(item);
    }

    public ArrayList<Item> getProductsList() {
        return this.products;
    }

    public void loadItems() {
        products.add(new Item("A", 10.00));
        products.add(new Item("B", 5.00));
        products.add(new Item("C", 20.00));
    }

    public void changePrice(String nazwa, Double price) {
        Boolean status = false;
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getName().equals(nazwa)) {
                this.products.get(i).setPrice(price);
                status = true;
                break;
            }
        }
        if (status) {
            ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
            ProductsServiceProducer psp = (ProductsServiceProducer) context.getBean("ProductsServiceProducer");
            psp.sendProductsList(products);
        }
    }

    public void changingPriceUI() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("---Change price---");
        System.out.println("---Name product:");
        System.out.print(">>");
        String name = reader.readLine();
        System.out.println("---New price:");
        System.out.print(">>");
        String price = reader.readLine();
        this.changePrice(name, Double.parseDouble(price));
    }
}
