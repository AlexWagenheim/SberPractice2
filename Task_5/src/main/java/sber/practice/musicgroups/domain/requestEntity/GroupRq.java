package sber.practice.musicgroups.domain.requestEntity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class GroupRq {
    @NotNull
    @NotEmpty(message = "Название не может быть пустым")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
