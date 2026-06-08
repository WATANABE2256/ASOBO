package com.asobo.controller;

import com.asobo.model.GroupEntity;
import com.asobo.model.Spot;
import com.asobo.service.SessionService;
import com.asobo.service.SpotService;
import com.asobo.service.VoteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/voting")
public class VotingController {

    private final SessionService sessionService;
    private final SpotService spotService;
    private final VoteService voteService;

    public VotingController(
            SessionService sessionService,
            SpotService spotService,
            VoteService voteService) {
        this.sessionService = sessionService;
        this.spotService = spotService;
        this.voteService = voteService;
    }

    @GetMapping
    public String voting(
            @RequestParam Long spotId,
            HttpSession session,
            Model model) {
        GroupEntity group = sessionService.getCurrentGroup(session);
        Spot spot = spotService.findById(spotId);

        model.addAttribute("group", group);
        model.addAttribute("spot", spot);
        model.addAttribute("statuses", voteService.getVoteStatuses(group.getId(), spotId));
        model.addAttribute("votedCount", voteService.getVotedCount(group.getId(), spotId));
        model.addAttribute("memberCount", voteService.getMemberCount(group.getId()));
        model.addAttribute("activeTab", "search");
        return "voting";
    }
}
