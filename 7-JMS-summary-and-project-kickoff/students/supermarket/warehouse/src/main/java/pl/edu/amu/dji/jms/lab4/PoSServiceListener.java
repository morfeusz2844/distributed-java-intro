package pl.edu.amu.dji.jms.lab4;


import com.google.common.base.Preconditions;
import java.util.ArrayList;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class PoSServiceListener implements MessageListener {
    
    private JmsTemplate jmsTemplate;
    
    private ArrayList<Item> products;

    public void setProducts(ArrayList<Item> products) {
        this.products = products;
    }
    
    public void setJmsTemplate(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }
    
    @Override
    public void onMessage(Message message) {
        Preconditions.checkArgument(message instanceof TextMessage);       
        TextMessage textMessage = (TextMessage) message;
        ApplicationContext context =  new ClassPathXmlApplicationContext("/context.xml");
        ProductsServiceProducer psp = (ProductsServiceProducer) context.getBean("ProductsServiceProducer");
        psp.sendProductsList(products);
    }    
}
