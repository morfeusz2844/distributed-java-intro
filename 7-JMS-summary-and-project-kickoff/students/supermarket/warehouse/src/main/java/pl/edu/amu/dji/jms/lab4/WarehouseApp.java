package pl.edu.amu.dji.jms.lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.jms.JMSException;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class WarehouseApp {
    
    private static Products products = new Products();

    public static void main(String[] args) throws JMSException, IOException {

        boolean isRunning=true;
        
        ApplicationContext context = new ClassPathXmlApplicationContext("contextListener.xml");
        PoSServiceListener psl = (PoSServiceListener) context.getBean("PoSServiceListener");
        
        System.out.println("---System Warehouse---");
        System.out.println("---Enter: EXIT to quit application---");
        System.out.println("---Enter: CHANGE to change price product---");
        
        
        psl.setProducts(products.getProductsList());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(isRunning){
            System.out.print(">>");
            String line = reader.readLine();
            switch(line){
                case "EXIT":{
                    isRunning= false;
                    break;
                }
                case "CHANGE":{
                    products.changingPriceUI();
                    break;
                }
                default:{
                    System.out.println("Unknow expression");
                    break;
                }
            }
            
        }
        ((AbstractApplicationContext)context).close();
        
    }
}
