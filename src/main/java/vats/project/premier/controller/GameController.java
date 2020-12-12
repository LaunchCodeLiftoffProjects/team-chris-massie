package vats.project.premier.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vats.project.premier.models.Game;
import vats.project.premier.models.data.AchievementRepository;
import vats.project.premier.models.data.GameRepository;
import vats.project.premier.models.data.ReviewRepository;

import javax.validation.Valid;

@Controller("games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AchievementRepository achievementRepository;

    @GetMapping("")
    public String displayGamesForm(Model model) {
        model.addAttribute(new Game());
        model.addAttribute("Games form", "Games");
        return "games";
    }

    @PostMapping("")
    public String processGamesForm(@ModelAttribute @Valid Game newGame, Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Game");
            return "game";
        }
        gameRepository.save(newGame);
        return "games";
    }
}
