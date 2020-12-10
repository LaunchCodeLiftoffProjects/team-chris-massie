package vats.project.premier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AchievementController {

    @Autowired
    private AchievementController achievementRepository;

    @RequestMapping("achievements")
    public String displayAchievementsForm(Model model) {
        model.addAttribute("Achievements form", "Achievements");

        return "achievements";
    }
}
