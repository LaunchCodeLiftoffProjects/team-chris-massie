package vats.project.premier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vats.project.premier.models.Achievement;
import vats.project.premier.models.data.AchievementRepository;

import javax.validation.Valid;

@Controller
public class AchievementController {

    @Autowired
    private AchievementRepository achievementRepository;

    @RequestMapping("achievements")
    public String displayAchievementsForm(Model model) {
        model.addAttribute("Achievements form", "Achievements");
        model.addAttribute(new Achievement());
        return "achievements";
    }

    @PostMapping("achievements")
    public String processGamesForm(@ModelAttribute @Valid Achievement newAchievement, Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add an Achievement");
            return "achievements";
        }
        achievementRepository.save(newAchievement);
        return "achievements";
    }
}
