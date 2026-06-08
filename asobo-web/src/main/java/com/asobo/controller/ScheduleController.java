package com.asobo.controller;

import com.asobo.model.PlanStatus;
import com.asobo.service.PlanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final PlanService planService;

    public ScheduleController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public String schedule(
            @RequestParam(defaultValue = "UPCOMING") PlanStatus status,
            Model model) {
        model.addAttribute("plans", planService.findByStatus(status));
        model.addAttribute("status", status);
        model.addAttribute("activeTab", "schedule");
        return "schedule";
    }
}
