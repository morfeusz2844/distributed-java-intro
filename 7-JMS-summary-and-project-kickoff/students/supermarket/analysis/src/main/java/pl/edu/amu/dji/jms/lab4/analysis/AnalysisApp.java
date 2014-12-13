package pl.edu.amu.dji.jms.lab4.analysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class AnalysisApp {

    public static void main(String[] args) throws IOException {
        Boolean isRunning = true;
        Report report = new Report();
        ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
        AnalysisServiceListener asl = (AnalysisServiceListener) context.getBean("AnalysisServiceListener");
        asl.setReport(report);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("---Reporting System Menu---");
        System.out.println("---Enter: REPORT to generate report---");
        System.out.println("---Enter: EXIT to quit---");
        
        
        while (isRunning) {
            System.out.print(">> ");
            String line = reader.readLine();
            switch (line) {
                case "EXIT":{
                    reader.close();
                    isRunning = false;
                    break;
                }
                case "REPORT":{
                    report.showReport();
                    break;
                }
                default:{
                    System.out.println("Unknow expression");
                }
            }

        }
        ((AbstractApplicationContext)context).close();
    }
}
