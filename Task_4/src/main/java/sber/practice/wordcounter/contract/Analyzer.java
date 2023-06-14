package sber.practice.wordcounter.contract;

import java.io.IOException;
import java.util.Map;

public interface Analyzer {

    Map<String, Integer> getAnalyzeResult(String filename) throws IOException;
}
