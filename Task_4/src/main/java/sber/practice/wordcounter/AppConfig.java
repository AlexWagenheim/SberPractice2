package sber.practice.wordcounter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sber.practice.wordcounter.service.Analyzer;
import sber.practice.wordcounter.service.Parser;
import sber.practice.wordcounter.service.ReportBuilder;
import sber.practice.wordcounter.service.implementation.AnalyzerImpl;
import sber.practice.wordcounter.service.implementation.TextParserImpl;
import sber.practice.wordcounter.service.implementation.TextReportBuilderImpl;

@Configuration
public class AppConfig {

    @Bean
    public Parser parser() {
        return new TextParserImpl();
    }

    @Bean
    public Analyzer analyzer() {
        return new AnalyzerImpl();
    }

    @Bean
    public ReportBuilder reportBuilder() {
        return new TextReportBuilderImpl();
    }
}
