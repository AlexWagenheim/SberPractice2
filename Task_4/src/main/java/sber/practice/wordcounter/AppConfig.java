package sber.practice.wordcounter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sber.practice.wordcounter.contract.Analyzer;
import sber.practice.wordcounter.contract.Parser;
import sber.practice.wordcounter.contract.ReportBuilder;
import sber.practice.wordcounter.implementation.AnalyzerImpl;
import sber.practice.wordcounter.implementation.ParserTXT;
import sber.practice.wordcounter.implementation.ReportBuilderTXT;

@Configuration
public class AppConfig {

    @Bean
    public Parser parser() {
        return new ParserTXT();
    }

    @Bean
    public Analyzer analyzer() {
        return new AnalyzerImpl();
    }

    @Bean
    public ReportBuilder reportBuilder() {
        return new ReportBuilderTXT();
    }
}
