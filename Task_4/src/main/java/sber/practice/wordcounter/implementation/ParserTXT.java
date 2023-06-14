package sber.practice.wordcounter.implementation;

import sber.practice.wordcounter.contract.Parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ParserTXT implements Parser {

    private List<String> file;
    private int position;

    @Override
    public void prepare(String filename) throws IOException {
        this.position = -1;
        FileReader fileReader = new FileReader(filename);
        StringBuilder stringBuilder = new StringBuilder();
        int b;
        char previous = 0;

        while ((b = fileReader.read()) != - 1) {
            if ((((char) b) != ' ') || (previous != ' ')) {
                stringBuilder.append((char) b);
            }
            previous = (char) b;
        }
        this.file = Arrays.stream(stringBuilder.toString().split(" ")).toList();
    }

    @Override
    public boolean hasNext() {
        return file.size() > position + 1;
    }

    @Override
    public String getNext() throws IOException {
        if (this.hasNext()) {
            position++;
            return file.get(position);
        } else {
            throw new IOException("Достигнут конец файла");
        }
    }
}
