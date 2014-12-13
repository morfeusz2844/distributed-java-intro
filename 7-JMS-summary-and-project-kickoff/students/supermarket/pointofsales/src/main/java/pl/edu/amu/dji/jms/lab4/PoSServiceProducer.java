package pl.edu.amu.dji.jms.lab4;

import javax.jms.*;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class PoSServiceProducer {
    private JmsTemplate jmsTemplate;
    private Destination POSQueue;
    
    public void setJmsTemplate(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }
    public void setPOSQueue(Destination POSQueue){
        this.POSQueue=POSQueue;
    }
    
    public void sendPOSInfo(){
        this.jmsTemplate.send(this.POSQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage("TESTER");
                return message;
            }
        });
    }
}
