package pl.edu.amu.dji.jms.lab4.analysis;

import java.util.HashMap;
import java.util.Map;

public class Report {

    public static final Map<String, Double> PRODUCTS = new HashMap<String, Double>();

    public void updateReport(String name, Double price) {
        Double current = PRODUCTS.get(name);

        current = current != null ? current + price : price;

        PRODUCTS.put(name, current);
    }

    public void showReport() {
        if (!PRODUCTS.isEmpty()) {
            for (Map.Entry entry : this.PRODUCTS.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        } else {
            System.out.println("No sales");
        }
    }
}
