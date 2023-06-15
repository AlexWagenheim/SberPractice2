package sber.practice.musicgroups.domain;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class GroupEntity {
    @NotNull
    private long id;
    @NotNull
    private String name;
    @NotNull
    private List<AlbumEntity> albumEntityList;

    public GroupEntity() {}

    public GroupEntity(long id, String name, List<AlbumEntity> albumEntityList) {
        this.id = id;
        this.name = name;
        this.albumEntityList = albumEntityList;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<AlbumEntity> getAlbumEntityList() {
        return albumEntityList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlbumEntityList(List<AlbumEntity> albumEntityList) {
        this.albumEntityList = albumEntityList;
    }
}
