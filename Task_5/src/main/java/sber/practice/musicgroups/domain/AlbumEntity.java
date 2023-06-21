package sber.practice.musicgroups.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TrackEntity> trackEntitySet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private GroupEntity group;

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public AlbumEntity() {}

    public AlbumEntity(long id, String name, int releaseYear, Set<TrackEntity> trackEntitySet) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.trackEntitySet = trackEntitySet;
    }

    public AlbumEntity(String name, int releaseYear, Set<TrackEntity> trackEntitySet) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.trackEntitySet = trackEntitySet;
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

    public Set<TrackEntity> getTrackEntitySet() {
        return trackEntitySet;
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

    public void addTrack(TrackEntity track) {
        trackEntitySet.add(track);
        track.setAlbum(this);
    }

    public void removeTrack(TrackEntity track) {
        trackEntitySet.remove(track);
        track.setAlbum(null);
    }

    public void setTrackEntitySet(Set<TrackEntity> trackEntitySet) {
        this.trackEntitySet = trackEntitySet;
    }
}
