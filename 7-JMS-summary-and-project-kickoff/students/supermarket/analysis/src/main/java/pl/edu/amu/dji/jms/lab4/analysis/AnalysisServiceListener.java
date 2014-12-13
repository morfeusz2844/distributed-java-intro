package pl.edu.amu.dji.jms.lab4.analysis;

import com.google.common.base.Preconditions;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import org.springframework.jms.core.JmsTemplate;
import pl.edu.amu.dji.jms.lab4.Item;

public class AnalysisServiceListener implements MessageListener{
        private JmsTemplate jmsTemplate;
        private volatile Report report;
    
    public void setJmsTemplate(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }
    public synchronized void setReport(Report report){
        this.report=report;
    }
    public void onMessage(Message message) {
        Preconditions.checkArgument(message instanceof ObjectMessage);       
        ObjectMessage objectMessage = (ObjectMessage) message;
            try {
                Item item = (Item) objectMessage.getObject();
                report.updateReport(item.getName(), item.getPrice());
//                System.out.println(item.getName());
            } catch (JMSException ex) {
                Logger.getLogger(AnalysisServiceListener.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
