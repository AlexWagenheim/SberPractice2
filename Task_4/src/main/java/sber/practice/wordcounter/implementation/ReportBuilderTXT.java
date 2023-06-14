package sber.practice.wordcounter.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import sber.practice.wordcounter.AppConfig;
import sber.practice.wordcounter.ResultItem;
import sber.practice.wordcounter.contract.Analyzer;
import sber.practice.wordcounter.contract.ReportBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@PropertySource("application.properties")
public class ReportBuilderTXT implements ReportBuilder {

//    @Value("${inputFile.fileName}")               //@Value почему-то не работает ни для полей, ни для сеттеров
    private String inputFileName;
    private String outputFileName;

    @Value("Task_4/src/main/resources/textFiles/FishText.txt")
    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    @Value("Task_4/src/main/resources/textFiles/result.txt")
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

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
