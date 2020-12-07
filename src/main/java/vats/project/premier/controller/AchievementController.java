package vats.project.premier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("achievements")
public class AchievementController {

    @Autowired
    private AchievementController achievementRepository;

    @GetMapping("")
    public String displayAchievementsPage(Model model) {
        model.addAttribute("Achievement Page", "Achievements");

        return "achievements";
    }
}
