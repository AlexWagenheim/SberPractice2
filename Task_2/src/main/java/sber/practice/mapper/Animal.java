package sber.practice.mapper;

import java.util.Map;

public class Animal {
    private String title;
    private boolean isAvailable;
    private boolean isSold;
    private Map<String, String> photosMap;

    @Override
    public String toString() {
        return "Animal{" +
                "title='" + title + '\'' +
                ", isAvailable=" + isAvailable +
                ", isSold=" + isSold +
                ", photosMap=" + photosMap +
                '}';
    }
}
