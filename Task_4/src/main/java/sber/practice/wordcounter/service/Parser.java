package sber.practice.wordcounter.service;

import java.io.IOException;

public interface Parser {

    void prepare(String filename) throws IOException;

    boolean hasNext();

    String getNext() throws IOException;
}
