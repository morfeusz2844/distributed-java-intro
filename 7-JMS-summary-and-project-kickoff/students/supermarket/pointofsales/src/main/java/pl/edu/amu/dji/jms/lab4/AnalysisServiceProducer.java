package pl.edu.amu.dji.jms.lab4;

import javax.jms.*;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class AnalysisServiceProducer {

    private JmsTemplate jmsTemplate;
    private Destination AnalysisQueue;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setAnalysisQueue(Destination AnalysisQueue) {
        this.AnalysisQueue = AnalysisQueue;
    }
    
    public void sendAnalysisInfo(final Item item){
        this.jmsTemplate.send(this.AnalysisQueue, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage message = session.createObjectMessage(item);
                return message;
            }
        });
    }
    
}
