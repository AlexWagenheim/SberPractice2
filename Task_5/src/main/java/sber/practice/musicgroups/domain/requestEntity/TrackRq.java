package sber.practice.musicgroups.domain.requestEntity;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TrackRq {
    @NotNull
    @NotEmpty(message = "Название не может быть пустым")
    private String name;
    @NotNull
    @Min(value = 1, message = "Длительность трека не может быть меньше или равна нулю")
    @Digits(integer=10, fraction=0, message = "Длительность должна задаваться числом")
    private String duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
