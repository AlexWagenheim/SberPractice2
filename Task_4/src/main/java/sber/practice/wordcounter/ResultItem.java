package sber.practice.wordcounter;

public class ResultItem {
    private String key;
    private int value;

    private ResultItem() {}

    public ResultItem(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}
