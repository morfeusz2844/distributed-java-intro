package pl.edu.amu.dji.jms.lab1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class HelloMain {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

                Connection connection = null;
        Session session = null;
        //Destination queue = null;
        Destination topic = null;
        MessageConsumer consumer = null;
        connection = connectionFactory.createConnection();
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //queue = session.createQueue("SayHelloQueue");
        topic = session.createTopic("SayHelloTopic");
        
        //consumer = session.createConsumer(queue);
        consumer = session.createConsumer(topic);

        MessageListener helloListener = new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try{
                    String text = textMessage.getText();
                    System.out.println("["+Thread.currentThread().getName()+"]"+"Hello "+text);
                } catch (JMSException e){
                    e.printStackTrace();
                }
               
            }
        };
        consumer.setMessageListener(helloListener);
        //Set MessageListener implementation as a message listener in MessageConsumer

        connection.start();
    }
}
