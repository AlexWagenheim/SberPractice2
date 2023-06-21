package sber.practice.musicgroups.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sber.practice.musicgroups.domain.AlbumEntity;
import sber.practice.musicgroups.domain.GroupEntity;
import sber.practice.musicgroups.domain.TrackEntity;
import sber.practice.musicgroups.domain.requestEntity.AlbumRq;
import sber.practice.musicgroups.domain.requestEntity.GroupRq;
import sber.practice.musicgroups.domain.requestEntity.TrackRq;
import sber.practice.musicgroups.repository.AlbumEntityRepository;
import sber.practice.musicgroups.repository.GroupEntityRepository;
import sber.practice.musicgroups.repository.TrackEntityRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    private GroupEntityRepository groupRepository;
    private AlbumEntityRepository albumRepository;
    private TrackEntityRepository trackRepository;

    @Autowired
    public AppController(GroupEntityRepository groupRepository, AlbumEntityRepository albumRepository, TrackEntityRepository trackRepository) {
        this.groupRepository = groupRepository;
        this.albumRepository = albumRepository;
        this.trackRepository = trackRepository;
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public String index(Model model) {
        List<GroupEntity> groupList = groupRepository.findAll();
        model.addAttribute("groupList", groupList);
        return "index";
    }

    @RequestMapping(value = "/group/{groupId}", method = RequestMethod.GET)
    public String group(@PathVariable("groupId") long groupId, Model model) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            model.addAttribute("group", optionalGroup.get());
            return "group";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/group/{groupId}/album/{albumId}", method = RequestMethod.GET)
    public String album(@PathVariable("groupId") long groupId, @PathVariable("albumId") long albumId, Model model) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(groupId);
        Optional<AlbumEntity> optionalAlbum = albumRepository.findById(albumId);
        if ((optionalGroup.isPresent()) && (optionalAlbum.isPresent())) {
            model.addAttribute("groupId", groupId);
            model.addAttribute("albumId", albumId);
            model.addAttribute("album", optionalAlbum.get());
            return "album";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirect(Model model) {
        return "redirect:/group";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String pageNotFound(Model model) {
        return "error";
    }

    @RequestMapping(value = "/group/add", method = RequestMethod.GET)
    public String getCreateGroupPage(Model model) {
        model.addAttribute("group", new GroupRq());
        return "createEntityPage/createGroup";
    }

    @RequestMapping(value = "/group/add", method = RequestMethod.POST)
    public String createGroup(@ModelAttribute("group") @Valid GroupRq groupRq, Errors errors, Model model) {
        try {
            if (errors.hasErrors()) {
                return "createEntityPage/createGroup";
            } else {
                groupRepository.save(new GroupEntity(0,
                        groupRq.getName(), new HashSet<>()));
                return "redirect:/group";
            }
        } catch (Exception e) {
            return "createEntityPage/createGroup";
        }
    }

    @RequestMapping(value = "/group/{groupId}/album/add", method = RequestMethod.GET)
    public String getCreateAlbumPage(@PathVariable("groupId") long groupId, Model model) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            model.addAttribute("groupId", groupId);
            model.addAttribute("album", new AlbumRq());
            return "createEntityPage/createAlbum";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/group/{groupId}/album/add", method = RequestMethod.POST)
    public String createAlbum(@PathVariable("groupId") long groupId,
                              @ModelAttribute("album") @Valid AlbumRq albumRq, Errors errors, Model model) {
        try {
            if (errors.hasErrors()) {
                return "createEntityPage/createAlbum";
            } else {
                Optional<GroupEntity> optionalGroup = groupRepository.findById(groupId);
                if (optionalGroup.isPresent()) {
                    optionalGroup.get().addAlbum(new AlbumEntity(albumRq.getName(), Integer.parseInt(albumRq.getYear()),
                                    new HashSet<>()));
                    groupRepository.save(optionalGroup.get());
                    return String.format("redirect:/group/%d", groupId);
                } else {
                    return "error";
                }
            }
        } catch (Exception e) {
            return "createEntityPage/createAlbum";
        }
    }

    @RequestMapping(value = "/group/{groupId}/album/{albumId}/track/add", method = RequestMethod.GET)
    public String getCreateTrackPage(@PathVariable("groupId") long groupId, @PathVariable("albumId") long albumId, Model model) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(groupId);
        Optional<AlbumEntity> optionalAlbum = albumRepository.findById(albumId);
        if ((optionalGroup.isPresent()) && (optionalAlbum.isPresent())) {
            model.addAttribute("groupId", groupId);
            model.addAttribute("albumId", albumId);
            model.addAttribute("track", new TrackRq());
            return "createEntityPage/createTrack";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/group/{groupId}/album/{albumId}/track/add", method = RequestMethod.POST)
    public String createTrack(@PathVariable("groupId") long groupId, @PathVariable("albumId") long albumId,
                              @ModelAttribute("track") @Valid TrackRq trackRq, Errors errors, Model model) {
        try {
            if (errors.hasErrors()) {
                return "createEntityPage/createTrack";
            } else {
                Optional<GroupEntity> optionalGroup = groupRepository.findById(groupId);
                Optional<AlbumEntity> optionalAlbum = albumRepository.findById(albumId);
                if ((optionalGroup.isPresent()) && (optionalAlbum.isPresent())) {
                    optionalAlbum.get().addTrack(new TrackEntity(trackRq.getName(), Integer.parseInt(trackRq.getDuration())));
                    albumRepository.save(optionalAlbum.get());
                    return String.format("redirect:/group/%d/album/%d", groupId, albumId);
                } else {
                    return "error";
                }
            }
        } catch (Exception e) {
            return "createEntityPage/createTrack";
        }
    }

    @RequestMapping(value = "/group/{groupId}/delete", method = RequestMethod.GET)
    public String deleteGroup(@PathVariable("groupId") long groupId, Model model) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            groupRepository.delete(optionalGroup.get());
            return "redirect:/group";
        } else {
            return "error";
        }

    }

    @RequestMapping(value = "/group/{groupId}/album/{albumId}/delete", method = RequestMethod.GET)
    public String deleteAlbum(@PathVariable("groupId") long groupId, @PathVariable("albumId") long albumId, Model model) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(groupId);
        Optional<AlbumEntity> optionalAlbum = albumRepository.findById(albumId);
        if ((optionalGroup.isPresent()) && (optionalAlbum.isPresent())) {
            optionalGroup.get().removeAlbum(optionalAlbum.get());
            groupRepository.save(optionalGroup.get());
            return "redirect:/group/" + groupId;
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/group/{groupId}/album/{albumId}/track/{trackId}/delete", method = RequestMethod.GET)
    public String deleteTrack(@PathVariable("groupId") long groupId, @PathVariable("albumId") long albumId,
                              @PathVariable("trackId") long trackId, Model model) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(groupId);
        Optional<AlbumEntity> optionalAlbum = albumRepository.findById(albumId);
        Optional<TrackEntity> optionalTrack = trackRepository.findById(trackId);
        if ((optionalGroup.isPresent()) && (optionalAlbum.isPresent()) && (optionalTrack.isPresent())) {
            optionalAlbum.get().removeTrack(optionalTrack.get());
            albumRepository.save(optionalAlbum.get());
            return "redirect:/group/" + groupId + "/album/" + albumId;
        } else {
            return "error";
        }
    }
}
