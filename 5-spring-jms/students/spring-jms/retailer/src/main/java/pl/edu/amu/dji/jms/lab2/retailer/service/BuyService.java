package pl.edu.amu.dji.jms.lab2.retailer.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import org.springframework.jms.core.MessageCreator;

public class BuyService implements MessageListener {

    private JmsTemplate jmsTemplate;

    private Double maxPrice;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public void onMessage(Message message) {
        try {
            MapMessage map = (MapMessage) message;
            double price = map.getDouble("price");
            if (maxPrice.compareTo(price)==1){
                jmsTemplate.send(message.getJMSReplyTo(), new MessageCreator() {

                    public Message createMessage(Session sn) throws JMSException {
                        MapMessage maps = sn.createMapMessage();
                        //TODO:
                    }
                });
            }
        } catch (JMSException ex) {
            Logger.getLogger(BuyService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
