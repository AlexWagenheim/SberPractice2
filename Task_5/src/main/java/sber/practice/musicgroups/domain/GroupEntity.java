package sber.practice.musicgroups.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @NotEmpty(message = "Название не может быть пустым")
    private String name;
    @NotNull
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AlbumEntity> albumEntitySet = new HashSet<>();

    public GroupEntity() {}

    public GroupEntity(long id, String name, Set<AlbumEntity> albumEntitySet) {
        this.id = id;
        this.name = name;
        this.albumEntitySet = albumEntitySet;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<AlbumEntity> getAlbumEntitySet() {
        return albumEntitySet;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAlbum(AlbumEntity album) {
        albumEntitySet.add(album);
        album.setGroup(this);
    }

    public void removeAlbum(AlbumEntity album) {
        albumEntitySet.remove(album);
        album.setGroup(null);
    }

    public void setAlbumEntitySet(Set<AlbumEntity> albumEntitySet) {
        this.albumEntitySet = albumEntitySet;
    }
}
