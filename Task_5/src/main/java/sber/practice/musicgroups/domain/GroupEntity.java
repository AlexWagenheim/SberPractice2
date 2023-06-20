package sber.practice.musicgroups.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @NotEmpty(message = "Название не может быть пустым")
    private String name;
    @NotNull
    @OneToMany
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
