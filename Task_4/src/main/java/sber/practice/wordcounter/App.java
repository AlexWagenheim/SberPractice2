package sber.practice.wordcounter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sber.practice.wordcounter.service.ReportBuilder;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        ReportBuilder reportBuilder = applicationContext.getBean(ReportBuilder.class);

        try {
            reportBuilder.makeReport();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
