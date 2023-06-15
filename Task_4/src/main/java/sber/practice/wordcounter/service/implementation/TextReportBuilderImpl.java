package sber.practice.wordcounter.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import sber.practice.wordcounter.AppConfig;
import sber.practice.wordcounter.service.Analyzer;
import sber.practice.wordcounter.service.ReportBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class TextReportBuilderImpl implements ReportBuilder {

//    @Value("${inputFile}")                                            //Не работает
    @Value("Task_4/src/main/resources/textFiles/FishText.txt")
    private String inputFileName;

    @Value("Task_4/src/main/resources/textFiles/result.txt")
    private String outputFileName;

    private void writeInFile(int count, List<ResultItem> resultList) throws IOException {
        FileWriter writer = new FileWriter(outputFileName, false);

        writer.append("Общее число: " + count + "\n");
        for (ResultItem item: resultList) {
            writer.append(item.getKey() + "         " + item.getValue() + "\n");
        }

        writer.flush();
    }

    @Override
    public void makeReport() throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Analyzer analyzer = applicationContext.getBean(Analyzer.class);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + inputFileName);
        Map<String, Integer> resultMap = analyzer.getAnalyzeResult(inputFileName);
        Set<String> keySet = resultMap.keySet();
        List<ResultItem> resultList = new ArrayList<>();
        int count = 0;

        for (String key: keySet) {
            resultList.add(new ResultItem(key, resultMap.get(key)));
            count += resultMap.get(key);
        }
        resultList.sort((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));

        writeInFile(count, resultList);
    }
}
