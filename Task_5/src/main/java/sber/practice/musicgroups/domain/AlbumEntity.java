package sber.practice.musicgroups.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class AlbumEntity {
    @NotNull
    private long id;
    @NotNull
    @NotEmpty(message = "Название не может быть пустым")
    private String name;
    @NotNull
    private int year;
    @NotNull
    private List<TrackEntity> trackEntityList;

    public AlbumEntity() {}

    public AlbumEntity(long id, String name, int year, List<TrackEntity> trackEntityList) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.trackEntityList = trackEntityList;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public List<TrackEntity> getTrackEntityList() {
        return trackEntityList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTrackEntityList(List<TrackEntity> trackEntityList) {
        this.trackEntityList = trackEntityList;
    }
}
