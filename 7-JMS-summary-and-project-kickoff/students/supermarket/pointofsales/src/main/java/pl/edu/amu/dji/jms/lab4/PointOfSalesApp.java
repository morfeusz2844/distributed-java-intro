package pl.edu.amu.dji.jms.lab4;

import java.util.ArrayList;
import javax.jms.JMSException;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class PointOfSalesApp {

    public static void main(String[] args) throws JMSException, InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
        PoSServiceProducer poSServiceProducer = (PoSServiceProducer) context.getBean("PoSServiceProducer");
        AnalysisServiceProducer analysisServiceProducer = (AnalysisServiceProducer) context.getBean("AnalysisServiceProducer");
        ThreadSale sale = new ThreadSale(new ArrayList<Item>(), analysisServiceProducer);
        
        ApplicationContext contextListener = new ClassPathXmlApplicationContext("/contextListener.xml");
        ProductsServiceListener psl = (ProductsServiceListener) contextListener.getBean("ProductsServiceListener");
        psl.setSale(sale);
        
        poSServiceProducer.sendPOSInfo();

    }
}
