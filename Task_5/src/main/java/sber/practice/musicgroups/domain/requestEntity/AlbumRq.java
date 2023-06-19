package sber.practice.musicgroups.domain.requestEntity;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AlbumRq {
    @NotNull
    @NotEmpty(message = "Название не может быть пустым")
    private String name;
    @NotNull
    @Digits(integer=4, fraction=0, message = "Год должен задаваться числом")
    private String year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
