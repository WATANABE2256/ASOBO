package com.asobo.controller;

import com.asobo.model.GroupEntity;
import com.asobo.model.Spot;
import com.asobo.service.SessionService;
import com.asobo.service.SpotService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/decision")
public class DecisionController {

    private final SessionService sessionService;
    private final SpotService spotService;

    public DecisionController(SessionService sessionService, SpotService spotService) {
        this.sessionService = sessionService;
        this.spotService = spotService;
    }

    @GetMapping
    public String decision(
            @RequestParam Long spotId,
            HttpSession session,
            Model model) {
        GroupEntity group = sessionService.getCurrentGroup(session);
        Spot spot = spotService.findById(spotId);

        model.addAttribute("group", group);
        model.addAttribute("spot", spot);
        model.addAttribute("activeTab", "search");
        return "decision";
    }
}
