package sber.practice.musicgroups.restPoint;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sber.practice.musicgroups.CatalogService;
import sber.practice.musicgroups.domain.AlbumEntity;
import sber.practice.musicgroups.domain.GroupEntity;
import sber.practice.musicgroups.domain.TrackEntity;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
@Validated
public class AppController {

    private CatalogService catalogService;

    @Autowired
    public AppController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping("/group")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupEntity postGroup(@RequestBody @Valid GroupEntity group) {
        try {
            return catalogService.createGroup(group);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

    }

    @PostMapping("/group/{groupId}/album")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupEntity addAlbum(@PathVariable long groupId, @RequestBody @Valid AlbumEntity album) {
        try {
            return catalogService.addAlbum(groupId, album);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @PostMapping("/group/{groupId}/album/{albumId}/track")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupEntity addTrack(@PathVariable long groupId, @PathVariable long albumId, @RequestBody @Valid TrackEntity track) {
        try {
            return catalogService.addTrack(groupId, albumId, track);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @GetMapping("/group")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupEntity> getAllGroups() {
        return catalogService.getAllGroups();
    }

    @GetMapping("/group/{groupId}")
    @ResponseStatus(HttpStatus.OK)
    public GroupEntity getGroupById(@PathVariable long groupId) {
        try {
            return catalogService.getGroupById(groupId);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @GetMapping("/group/{groupId}/album")
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumEntity> getAlbumList(@PathVariable long groupId) {
        try {
            return catalogService.getAlbumListById(groupId);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @GetMapping("/group/{groupId}/album/{albumId}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumEntity getAlbumById(@PathVariable long groupId, @PathVariable long albumId) {
        try {
            return catalogService.getAlbumById(groupId, albumId);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @GetMapping("/group/{groupId}/album/{albumId}/track")
    @ResponseStatus(HttpStatus.OK)
    public List<TrackEntity> getTrackList(@PathVariable long groupId, @PathVariable long albumId) {
        try {
            return catalogService.getTrackListById(groupId, albumId);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @GetMapping("/group/{groupId}/album/{albumId}/track/{trackId}")
    @ResponseStatus(HttpStatus.OK)
    public TrackEntity getTrackById(@PathVariable long groupId, @PathVariable long albumId, @PathVariable long trackId) {
        try {
            return catalogService.getTrackById(groupId, albumId, trackId);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

}
