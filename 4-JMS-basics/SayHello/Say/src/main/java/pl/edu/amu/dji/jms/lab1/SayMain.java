package pl.edu.amu.dji.jms.lab1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SayMain {

    public static final String EXIT = "exit";

    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
          
        Connection connection = null;
        Session session = null;
        //Destination queue = null;
        Destination topic = null;
        MessageProducer producer = null;  
        connection = connectionFactory.createConnection();
        
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //queue = session.createQueue("SayHelloQueue");
        topic = session.createTopic("SayHelloTopic");
        
        //producer = session.createProducer(queue);
        producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        
        connection.start();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String text = "";
        while (!text.equalsIgnoreCase(EXIT)) {
            System.out.print("Say hello to:");
            text = bufferedReader.readLine();
            TextMessage message = session.createTextMessage(text);
            producer.send(message);
            
        }

        //Close stuff
        session.close();
        connection.close();
    }
}
