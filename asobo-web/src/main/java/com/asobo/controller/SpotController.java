package com.asobo.controller;

import com.asobo.model.SpotCategory;
import com.asobo.service.SpotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/spots")
public class SpotController {

    private final SpotService spotService;

    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }

    @GetMapping
    public String popular(
            @RequestParam(required = false) SpotCategory category,
            Model model) {
        if (category != null) {
            model.addAttribute("spots", spotService.findByCategory(category));
            model.addAttribute("selectedCategory", category);
        } else {
            model.addAttribute("spots", spotService.findAllPopular());
        }
        model.addAttribute("categories", SpotCategory.values());
        model.addAttribute("activeTab", "popular");
        return "spots/popular";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", SpotCategory.values());
        model.addAttribute("activeTab", "search");
        return "spots/categories";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("spot", spotService.findById(id));
        model.addAttribute("activeTab", "search");
        return "spots/detail";
    }
}
