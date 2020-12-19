package vats.project.premier.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vats.project.premier.models.Game;
import vats.project.premier.models.data.GameRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping("games")
    public String displayGamesForm(Model model) {
        model.addAttribute("Games form", "Games");
        model.addAttribute(new Game());
        return "games";
    }
    @PostMapping("games")
    public String processGamesForm(@ModelAttribute @Valid Game newGame, Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Game");
            return "game";
        }
        gameRepository.save(newGame);
        return "games";
    }
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Game");
        model.addAttribute(new Game());
        model.addAttribute("categories", gameRepository.findAll());
        return "create";
    }
}
