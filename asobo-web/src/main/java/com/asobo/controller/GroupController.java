package com.asobo.controller;

import com.asobo.model.GroupEntity;
import com.asobo.model.User;
import com.asobo.service.GroupService;
import com.asobo.service.SessionService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/groups")
@Validated
public class GroupController {

    private final GroupService groupService;
    private final SessionService sessionService;

    public GroupController(GroupService groupService, SessionService sessionService) {
        this.groupService = groupService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public String list(HttpSession session, Model model) {
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("currentGroup", sessionService.getCurrentGroup(session));
        model.addAttribute("activeTab", "mypage");
        return "groups/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("activeTab", "mypage");
        return "groups/create";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam @NotBlank String name,
            HttpSession session) {
        User user = sessionService.getCurrentUser(session);
        GroupEntity group = groupService.createGroup(name, user.getId());
        sessionService.setCurrentGroup(session, group.getId());
        return "redirect:/groups";
    }

    @PostMapping("/select")
    public String selectGroup(
            @RequestParam Long groupId,
            HttpSession session) {
        sessionService.setCurrentGroup(session, groupId);
        return "redirect:/swipe";
    }

    @GetMapping("/invite")
    public String invite(HttpSession session, Model model) {
        GroupEntity group = sessionService.getCurrentGroup(session);
        User inviter = sessionService.getCurrentUser(session);
        model.addAttribute("group", group);
        model.addAttribute("inviter", inviter);
        return "groups/invite";
    }
}
