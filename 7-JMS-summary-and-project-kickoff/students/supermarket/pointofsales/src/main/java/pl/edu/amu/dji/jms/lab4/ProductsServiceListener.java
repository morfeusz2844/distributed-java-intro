package pl.edu.amu.dji.jms.lab4;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.springframework.jms.core.JmsTemplate;

public class ProductsServiceListener implements MessageListener {

    private JmsTemplate jmsTemplate;

    private ArrayList<Item> products = new ArrayList<>();

    private ThreadSale sale;

    public void setSale(ThreadSale sale) {
        this.sale = sale;
    }

    public void setProducts(ArrayList<Item> products) {
        this.products = products;
    }

    public ArrayList<Item> getProducts() {
        return products;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void onMessage(Message message) {
        try {
            Preconditions.checkArgument(message instanceof ObjectMessage);
            ObjectMessage objectMessage = (ObjectMessage) message;
            this.sale.setProducts((ArrayList<Item>) objectMessage.getObject());
            if (sale.getIsRunning() == false) {
                new Thread(sale).start();
            }
        } catch (JMSException ex) {
            Logger.getLogger(ProductsServiceListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
