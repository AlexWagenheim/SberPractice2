package sber.practice.musicgroups;

import org.springframework.stereotype.Component;
import sber.practice.musicgroups.domain.AlbumEntity;
import sber.practice.musicgroups.domain.GroupEntity;
import sber.practice.musicgroups.domain.TrackEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * реализует добавление и удаление таких сущностей, как {@link GroupEntity}, {@link AlbumEntity}, {@link TrackEntity}
 */
@Component
public class CatalogService {

    private List<GroupEntity> groupEntityList;

    public CatalogService() {
        groupEntityList = new ArrayList<>();
    }

    /**
     * добавление новой <b>музыкальной группы</b> {@link GroupEntity}
     * @param group - музыкальная группа
     */
    public GroupEntity createGroup(GroupEntity group) {
        if(groupEntityList.stream().anyMatch(item -> item.getId() == group.getId())) {
            throw new IllegalArgumentException(String.format("Группа (id = %d) уже существует", group.getId()));
        } else {
            groupEntityList.add(group);
            return group;
        }
    }

    private GroupEntity addAlbumInGroup(GroupEntity group, AlbumEntity album) {
        if(group.getAlbumEntityList().stream().anyMatch(item -> item.getId() == album.getId())) {
            throw new IllegalArgumentException(String.format(
                    "У группы (id = %d) уже существует альбом(id = %d)", group.getId(), album.getId()));
        } else {
            HashSet<Long> set = new HashSet<>();
            if(album.getTrackEntityList().stream().mapToLong(TrackEntity::getId).allMatch(set::add)) {
                group.getAlbumEntityList().add(album);
                return group;
            } else {
                throw new IllegalArgumentException("Не все треки в альбоме имеют различные id");
            }
        }
    }

    /**
     * добавление нового <b>альбома</b> {@link AlbumEntity} по <b>id группы</b> {@link GroupEntity}
     * @param groupId - id музыкальной группы
     * @param album - альбом
     */
    public GroupEntity addAlbum(long groupId, AlbumEntity album) {
        Optional<GroupEntity> optionalGroup = groupEntityList.stream().filter(item -> item.getId() == groupId).findAny();
        if(optionalGroup.isPresent()) {
            return addAlbumInGroup(optionalGroup.get(), album);
        } else {
            throw new IllegalArgumentException(String.format("Группы (id = %d) не существует", groupId));
        }
    }

    /**
     * добавление нового <b>трека</b> {@link TrackEntity} по <b>id альбома</b> {@link AlbumEntity} и <b>id группы</b> {@link GroupEntity}
     * @param groupId - id музыкальной группы
     * @param albumId - id альбома
     * @param track - трек
     */
    public GroupEntity addTrack(long groupId, long albumId, TrackEntity track) {
        Optional<GroupEntity> optionalGroup = groupEntityList.stream().filter(item -> item.getId() == groupId).findAny();
        if(optionalGroup.isPresent()) {
            Optional<AlbumEntity> optionalAlbum = optionalGroup.get().getAlbumEntityList().
                    stream().filter(item -> item.getId() == albumId).findAny();
            if (optionalAlbum.isPresent()) {
                if(optionalAlbum.get().getTrackEntityList().
                        stream().anyMatch(item -> item.getId() == track.getId())) {
                    throw new IllegalArgumentException(String.format(
                            "У группы (id = %d) и альбома(id = %d) уже существует трек(id = %d)", groupId, albumId, track.getId()));
                } else {
                    optionalAlbum.get().getTrackEntityList().add(track);
                    return optionalGroup.get();
                }
            } else {
                throw new IllegalArgumentException(String.format("У группы (id = %d) не существует альбома(id = %d)", groupId, albumId));
            }
        } else {
            throw new IllegalArgumentException(String.format("Группы (id = %d) не существует", groupId));
        }
    }

    /**
     * получение <b>списка всех музыкальных групп</b> {@link GroupEntity}
     */
    public List<GroupEntity> getAllGroups() {
        return groupEntityList;
    }

    /**
     * получение <b>музыкальной группы</b> {@link GroupEntity} по <b>id группы</b>
     * @param groupId - id музыкальной группы
     */
    public GroupEntity getGroupById(long groupId) {
        Optional<GroupEntity> optionalGroup = groupEntityList.stream().filter(item -> item.getId() == groupId).findAny();
        if (optionalGroup.isPresent()) {
            return optionalGroup.get();
        } else {
            throw new IllegalArgumentException(String.format("Группы (id = %d) не существует", groupId));
        }
    }

    /**
     * получение <b>списка альбомов {@link AlbumEntity}</b> музыкальной группы {@link GroupEntity} по <b>id группы</b>
     * @param groupId - id музыкальной группы
     */
    public List<AlbumEntity> getAlbumListById(long groupId) {
        Optional<GroupEntity> optionalGroup = groupEntityList.stream().filter(item -> item.getId() == groupId).findAny();
        if (optionalGroup.isPresent()) {
            return optionalGroup.get().getAlbumEntityList();
        } else {
            throw new IllegalArgumentException(String.format("Группы (id = %d) не существует", groupId));
        }
    }

    /**
     * получение <b>альбома {@link AlbumEntity}</b> музыкальной группы {@link GroupEntity} по <b>id группы</b> и <b>id альбома</b>
     * @param groupId - id музыкальной группы
     * @param albumId - id альбома
     */
    public AlbumEntity getAlbumById(long groupId, long albumId) {
        Optional<GroupEntity> optionalGroup = groupEntityList.stream().filter(item -> item.getId() == groupId).findAny();
        if (optionalGroup.isPresent()) {
            Optional<AlbumEntity> optionalAlbum = optionalGroup.get().getAlbumEntityList().
                    stream().filter(item -> item.getId() == albumId).findAny();
            if (optionalAlbum.isPresent()) {
                return optionalAlbum.get();
            } else {
                throw new IllegalArgumentException(String.format("У группы (id = %d) не существует альбома(id = %d)", groupId, albumId));
            }
        } else {
            throw new IllegalArgumentException(String.format("Группы (id = %d) не существует", groupId));
        }
    }

    /**
     * получение <b>списка треков {@link TrackEntity}</b> в альбоме {@link AlbumEntity} музыкальной группы {@link GroupEntity} по <b>id группы</b> и <b>id альбома</b>
     * @param groupId - id музыкальной группы
     * @param albumId - id альбома
     */
    public List<TrackEntity> getTrackListById(long groupId, long albumId) {
        Optional<GroupEntity> optionalGroup = groupEntityList.stream().filter(item -> item.getId() == groupId).findAny();
        if (optionalGroup.isPresent()) {
            Optional<AlbumEntity> optionalAlbum = optionalGroup.get().getAlbumEntityList().
                    stream().filter(item -> item.getId() == albumId).findAny();
            if (optionalAlbum.isPresent()) {
                return optionalAlbum.get().getTrackEntityList();
            } else {
                throw new IllegalArgumentException(String.format("У группы (id = %d) не существует альбома(id = %d)", groupId, albumId));
            }
        } else {
            throw new IllegalArgumentException(String.format("Группы (id = %d) не существует", groupId));
        }
    }

    /**
     * получение <b>трека {@link TrackEntity}</b> в альбоме {@link AlbumEntity} музыкальной группы {@link GroupEntity} по <b>id группы</b>, <b>id альбома</b> и <b>id трека</b>
     * @param groupId - id музыкальной группы
     * @param albumId - id альбома
     * @param trackId - id трека
     */
    public TrackEntity getTrackById(long groupId, long albumId, long trackId) {
        Optional<GroupEntity> optionalGroup = groupEntityList.stream().filter(item -> item.getId() == groupId).findAny();
        if (optionalGroup.isPresent()) {
            Optional<AlbumEntity> optionalAlbum = optionalGroup.get().getAlbumEntityList().
                    stream().filter(item -> item.getId() == albumId).findAny();
            if (optionalAlbum.isPresent()) {
                Optional<TrackEntity> optionalTrack = optionalAlbum.get().getTrackEntityList().
                        stream().filter(item -> item.getId() == trackId).findAny();
                if (optionalTrack.isPresent()) {
                    return optionalTrack.get();
                } else {
                    throw new IllegalArgumentException(String.format(
                            "У группы (id = %d) и альбома(id = %d) не существует трека(id = %d)", groupId, albumId, trackId));
                }
            } else {
                throw new IllegalArgumentException(String.format("У группы (id = %d) не существует альбома(id = %d)", groupId, albumId));
            }
        } else {
            throw new IllegalArgumentException(String.format("Группы (id = %d) не существует", groupId));
        }
    }

    /**
     * возвращает следующий доступный <b>id</b> для назначения {@link GroupEntity}
     */
    public long getNextGroupId() {
        if (groupEntityList.isEmpty()) {
            return 1;
        } else {
            return groupEntityList.stream().mapToLong(GroupEntity::getId).max().getAsLong() + 1;
        }
    }

    /**
     * возвращает следующий доступный <b>id</b> для назначения {@link AlbumEntity}
     * @param groupId - id музыкальной группы
     */
    public long getNextAlbumId(long groupId) throws IllegalArgumentException{
        return getGroupById(groupId).getAlbumEntityList().stream().mapToLong(AlbumEntity::getId).max().getAsLong() + 1;
    }

    /**
     * возвращает следующий доступный <b>id</b> для назначения {@link TrackEntity}
     * @param groupId - id музыкальной группы
     * @param albumId - id альбома
     */
    public long getNextTrackId(long groupId, long albumId) throws IllegalArgumentException{
        return getAlbumById(groupId, albumId).getTrackEntityList().stream().mapToLong(TrackEntity::getId).max().getAsLong() + 1;
    }

    /**
     * удаление <b>музыкальной группы</b> {@link GroupEntity} по <b>id группы</b>
     * @param groupId - id музыкальной группы
     */
    public boolean deleteGroupById(long groupId) {
        Optional<GroupEntity> optionalGroup = groupEntityList.stream().filter(item -> item.getId() == groupId).findAny();
        if (optionalGroup.isPresent()) {
            return groupEntityList.remove(optionalGroup.get());
        } else {
            throw new IllegalArgumentException(String.format("Группы (id = %d) не существует", groupId));
        }
    }

    /**
     * удаление <b>альбома {@link AlbumEntity}</b> музыкальной группы {@link GroupEntity} по <b>id группы</b> и <b>id альбома</b>
     * @param groupId - id музыкальной группы
     * @param albumId - id альбома
     */
    public boolean deleteAlbumById(long groupId, long albumId) {
        Optional<GroupEntity> optionalGroup = groupEntityList.stream().filter(item -> item.getId() == groupId).findAny();
        if (optionalGroup.isPresent()) {
            Optional<AlbumEntity> optionalAlbum = optionalGroup.get().getAlbumEntityList().stream().filter(
                    item -> item.getId() == albumId).findAny();
            if (optionalAlbum.isPresent()) {
                return optionalGroup.get().getAlbumEntityList().remove(optionalAlbum.get());
            } else {
                throw new IllegalArgumentException(String.format("Группы (id = %d) не существует", groupId));
            }

        } else {
            throw new IllegalArgumentException(String.format("У группы (id = %d) не существует альбома(id = %d)", groupId, albumId));
        }
    }

    /**
     * удаление <b>трека {@link TrackEntity}</b> в альбоме {@link AlbumEntity} музыкальной группы {@link GroupEntity} по <b>id группы</b>, <b>id альбома</b> и <b>id трека</b>
     * @param groupId - id музыкальной группы
     * @param albumId - id альбома
     * @param trackId - id трека
     */
    public boolean deleterackById(long groupId, long albumId, long trackId) {
        Optional<GroupEntity> optionalGroup = groupEntityList.stream().filter(item -> item.getId() == groupId).findAny();
        if (optionalGroup.isPresent()) {
            Optional<AlbumEntity> optionalAlbum = optionalGroup.get().getAlbumEntityList().
                    stream().filter(item -> item.getId() == albumId).findAny();
            if (optionalAlbum.isPresent()) {
                Optional<TrackEntity> optionalTrack = optionalAlbum.get().getTrackEntityList().
                        stream().filter(item -> item.getId() == trackId).findAny();
                if (optionalTrack.isPresent()) {
                    return optionalAlbum.get().getTrackEntityList().remove(optionalTrack.get());
                } else {
                    throw new IllegalArgumentException(String.format(
                            "У группы (id = %d) и альбома(id = %d) не существует трека(id = %d)", groupId, albumId, trackId));
                }
            } else {
                throw new IllegalArgumentException(String.format("У группы (id = %d) не существует альбома(id = %d)", groupId, albumId));
            }
        } else {
            throw new IllegalArgumentException(String.format("Группы (id = %d) не существует", groupId));
        }
    }
}
