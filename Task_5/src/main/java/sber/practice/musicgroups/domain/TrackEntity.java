package sber.practice.musicgroups.domain;

import jakarta.validation.constraints.NotNull;

public class TrackEntity {
    @NotNull
    private long id;
    @NotNull
    private String name;
    @NotNull
    private long duration;

    public TrackEntity() {}

    public TrackEntity(long id, String name, long duration) {
        this.id = id;
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
