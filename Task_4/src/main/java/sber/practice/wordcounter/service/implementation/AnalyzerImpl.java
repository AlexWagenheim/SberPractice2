package sber.practice.wordcounter.service.implementation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sber.practice.wordcounter.AppConfig;
import sber.practice.wordcounter.service.Analyzer;
import sber.practice.wordcounter.service.Parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AnalyzerImpl implements Analyzer {
    @Override
    public Map getAnalyzeResult(String filename) throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Parser parser = applicationContext.getBean(Parser.class);
        parser.prepare(filename);

        Map<String, Integer> wordMap = new HashMap<>();
        while (parser.hasNext()) {
            String key = parser.getNext().toUpperCase(Locale.ROOT).trim();
            if (wordMap.containsKey(key)) {
                wordMap.replace(key, wordMap.get(key) + 1);
            } else {
                wordMap.put(key, 1);
            }
        }

        return wordMap;
    }
}
