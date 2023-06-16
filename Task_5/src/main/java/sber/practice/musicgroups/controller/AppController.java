package sber.practice.musicgroups.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sber.practice.musicgroups.CatalogService;
import sber.practice.musicgroups.domain.AlbumEntity;
import sber.practice.musicgroups.domain.GroupEntity;

import java.util.List;

@Controller
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
}
