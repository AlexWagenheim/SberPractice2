package sber.practice.musicgroups.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty(message = "Название не может быть пустым")
    private String name;

    @NotNull
    private int releaseYear;

    @NotNull
    @OneToMany
    private List<TrackEntity> trackEntityList = new ArrayList<>();

    public AlbumEntity() {}

    public AlbumEntity(long id, String name, int releaseYear, List<TrackEntity> trackEntityList) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.trackEntityList = trackEntityList;
    }

    public AlbumEntity(String name, int releaseYear, List<TrackEntity> trackEntityList) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.trackEntityList = trackEntityList;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
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

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setTrackEntityList(List<TrackEntity> trackEntityList) {
        this.trackEntityList = trackEntityList;
    }
}
