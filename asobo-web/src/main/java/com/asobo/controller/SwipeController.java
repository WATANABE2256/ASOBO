package com.asobo.controller;

import com.asobo.model.GroupEntity;
import com.asobo.model.Spot;
import com.asobo.model.User;
import com.asobo.model.VoteType;
import com.asobo.service.SessionService;
import com.asobo.service.SpotService;
import com.asobo.service.VoteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/swipe")
public class SwipeController {

    private final SessionService sessionService;
    private final SpotService spotService;
    private final VoteService voteService;

    public SwipeController(
            SessionService sessionService,
            SpotService spotService,
            VoteService voteService) {
        this.sessionService = sessionService;
        this.spotService = spotService;
        this.voteService = voteService;
    }

    @GetMapping
    public String swipe(
            @RequestParam(required = false) Long spotId,
            HttpSession session,
            Model model) {
        GroupEntity group = sessionService.getCurrentGroup(session);
        User user = sessionService.getCurrentUser(session);

        Spot spot;
        if (spotId != null) {
            spot = spotService.findById(spotId);
        } else {
            spot = spotService.findAllPopular().get(0);
        }

        model.addAttribute("spot", spot);
        model.addAttribute("group", group);
        model.addAttribute("user", user);
        model.addAttribute("activeTab", "search");
        return "swipe";
    }

    @PostMapping("/vote")
    public String vote(
            @RequestParam Long spotId,
            @RequestParam VoteType voteType,
            HttpSession session) {
        GroupEntity group = sessionService.getCurrentGroup(session);
        User user = sessionService.getCurrentUser(session);
        Spot spot = spotService.findById(spotId);

        voteService.castVote(group, spot, user, voteType);

        if (voteService.isUnanimousWant(group.getId(), spot.getId())) {
            voteService.createPlanIfDecided(group, spot);
            return "redirect:/decision?spotId=" + spotId;
        }

        if (voteService.isAllVoted(group.getId(), spot.getId())) {
            Spot next = spotService.findNextForGroup(group.getId(), spotId);
            return "redirect:/swipe?spotId=" + next.getId();
        }

        return "redirect:/voting?spotId=" + spotId;
    }
}
