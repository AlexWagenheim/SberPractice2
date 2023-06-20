package sber.practice.musicgroups.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class TrackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty(message = "Название не может быть пустым")
    private String name;

    @NotNull
    @Min(value = 1, message = "Длительность трека не может быть меньше или равна нулю")
    private long duration;

    public TrackEntity() {}

    public TrackEntity(long id, String name, long duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public TrackEntity(String name, long duration) {
        this.name = name;
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getDuration() {
        return duration;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
