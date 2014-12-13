package pl.edu.amu.dji.jms.lab4;

import java.util.ArrayList;
import javax.jms.*;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ProductsServiceProducer {
    private JmsTemplate jmsTemplate;
    private Destination productsTopic;
    

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setProductsTopic(Destination productsTopic) {
        this.productsTopic = productsTopic;
    }
    
    public void sendProductsList(final ArrayList<Item> productsList){
        this.jmsTemplate.send(productsTopic, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage message = session.createObjectMessage();
                message.setObject(productsList);
//                System.out.println("Wyslano produkty");
                return message;
            }
        });
    }
}
