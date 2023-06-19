package sber.practice.musicgroups.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;
import sber.practice.musicgroups.CatalogService;
import sber.practice.musicgroups.domain.AlbumEntity;
import sber.practice.musicgroups.domain.GroupEntity;
import sber.practice.musicgroups.domain.TrackEntity;
import sber.practice.musicgroups.domain.requestEntity.AlbumRq;
import sber.practice.musicgroups.domain.requestEntity.GroupRq;
import sber.practice.musicgroups.domain.requestEntity.TrackRq;

import java.util.ArrayList;
import java.util.List;

@Controller
//@Validated
public class AppController {

    private CatalogService catalogService;

    @Autowired
    public AppController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public String index(Model model) {
        List<GroupEntity> groupList = catalogService.getAllGroups();
        model.addAttribute("groupList", groupList);
        return "index";
    }

    @RequestMapping(value = "/group/{groupId}", method = RequestMethod.GET)
    public String group(@PathVariable("groupId") long groupId, Model model) {
        try {
            GroupEntity groupEntity = catalogService.getGroupById(groupId);
            model.addAttribute("group", groupEntity);
            return "group";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/group/{groupId}/album/{albumId}", method = RequestMethod.GET)
    public String album(@PathVariable("groupId") long groupId, @PathVariable("albumId") long albumId, Model model) {
        try {
            AlbumEntity albumEntity = catalogService.getAlbumById(groupId, albumId);
            model.addAttribute("groupId", groupId);
            model.addAttribute("albumId", albumId);
            model.addAttribute("album", albumEntity);
            return "album";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
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
        model.addAttribute("group", new GroupEntity());
        return "createEntityPage/createGroup";
    }

    @RequestMapping(value = "/group/add", method = RequestMethod.POST)
    public String createGroup(@ModelAttribute("group") @Valid GroupRq groupRq, Errors errors, Model model) {
        try {
            if (errors.hasErrors()) {
                return "createEntityPage/createGroup";
            } else {
                catalogService.createGroup(new GroupEntity(catalogService.getNextGroupId(),
                        groupRq.getName(), new ArrayList<AlbumEntity>()));
                return "redirect:/group";
            }
        } catch (IllegalArgumentException exception) {
            return "createEntityPage/createGroup";
        }
    }

    @RequestMapping(value = "/group/{groupId}/album/add", method = RequestMethod.GET)
    public String getCreateAlbumPage(@PathVariable("groupId") long groupId, Model model) {
        try {
            GroupEntity groupEntity = catalogService.getGroupById(groupId);
            model.addAttribute("groupId", groupId);
            model.addAttribute("album", new AlbumEntity());
            return "createEntityPage/createAlbum";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
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
                catalogService.addAlbum(groupId, new AlbumEntity(catalogService.getNextAlbumId(groupId),
                        albumRq.getName(), Integer.parseInt(albumRq.getYear()), new ArrayList<TrackEntity>()));
                return String.format("redirect:/group/%d", groupId);
            }
        } catch (IllegalArgumentException exception) {
            return "createEntityPage/createAlbum";
        }
    }

    @RequestMapping(value = "/group/{groupId}/album/{albumId}/track/add", method = RequestMethod.GET)
    public String getCreateTrackPage(@PathVariable("groupId") long groupId, @PathVariable("albumId") long albumId, Model model) {
        try {
            AlbumEntity albumEntity = catalogService.getAlbumById(groupId, albumId);
            model.addAttribute("groupId", groupId);
            model.addAttribute("albumId", albumId);
            model.addAttribute("track", new TrackEntity());
            return "createEntityPage/createTrack";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
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
                catalogService.addTrack(groupId, albumId,
                        new TrackEntity(catalogService.getNextTrackId(groupId, albumId),
                                trackRq.getName(), Integer.parseInt(trackRq.getDuration())));
                return String.format("redirect:/group/%d/album/%d", groupId, albumId);
            }
        } catch (IllegalArgumentException exception) {
            return "createEntityPage/createTrack";
        }
    }

    @RequestMapping(value = "/group/{groupId}", method = RequestMethod.DELETE)
    public String deleteGroup(@PathVariable("groupId") long groupId, Model model) {
        try {
            catalogService.deleteGroupById(groupId);
            return "redirect:/group";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/group/{groupId}/album/{albumId}", method = RequestMethod.DELETE)
    public String deleteAlbum(@PathVariable("groupId") long groupId, @PathVariable("albumId") long albumId, Model model) {
        try {
            catalogService.deleteAlbumById(groupId, albumId);
            return "redirect:/group/" + groupId;
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/group/{groupId}/album/{albumId}/track/{trackId}", method = RequestMethod.DELETE)
    public String deleteTrack(@PathVariable("groupId") long groupId, @PathVariable("albumId") long albumId,
                              @PathVariable("trackId") long trackId, Model model) {
        try {
            catalogService.deleterackById(groupId, albumId, trackId);
            return "redirect:/group/" + groupId + "/album/" + albumId;
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}
